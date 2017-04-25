package by.epam.vladlitvin.calculater;

import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.interpreter.Interpreper;
import by.epam.vladlitvin.type.TextElementType;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vlad_ on 4/24/2017.
 */
public class LexemeHandler {

    public void deleteLexeme(TextComponent component, char symbol) {

        Iterator sentenceIterator = component.getChildren().iterator();

        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();

            if (TextElementType.SENTENCE.equals(sentence.getElementType())) {
                Iterator<TextComponent> lexemeIterator = sentence.getChildren().iterator();

                while (lexemeIterator.hasNext()) {
                  TextComponent lexeme = lexemeIterator.next();

                  if (TextElementType.LEXEME.equals(lexeme.getElementType()) &&
                          (lexeme.getElement().toCharArray()[0] == symbol)) {
                      sentence.remove(lexeme);
                  }
                }
            } else if (!TextElementType.SYMBOL.equals(sentence.getElementType())) {
               deleteLexeme(sentence, symbol);
            }
        }
    }

    public void replaceLexeme(TextComponent component) {
        Iterator sentenceIterator = component.getChildren().iterator();
        int temporaryIndex = 0;
        TextComponent temporaryComponent = new TextElement();
        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();

            if (TextElementType.SENTENCE.equals(sentence.getElementType())) {
                Iterator<TextComponent> lexemeIterator = sentence.getChildren().iterator();

                while (lexemeIterator.hasNext()) {
                    TextComponent lexeme = lexemeIterator.next();

                    if (TextElementType.LEXEME.equals(lexeme.getElementType())) {
                        temporaryIndex = sentence.getChildren().indexOf(lexeme);
                        temporaryComponent = lexeme;
                        break;
                    }
                }
                lexemeIterator = sentence.getChildren().descendingIterator();
                while (lexemeIterator.hasNext()) {
                    TextComponent lexeme = lexemeIterator.next();

                    if (TextElementType.LEXEME.equals(lexeme.getElementType())) {
                        int temporaryIndex2;
                        temporaryIndex2 = sentence.getChildren().indexOf(lexeme);
                        TextComponent temporaryComponent2 = sentence.getChildren().get(temporaryIndex);
                        sentence.getChildren().set(temporaryIndex, temporaryComponent);
                        sentence.getChildren().set(temporaryIndex2, temporaryComponent2);
                        break;
                    }
                }
            } else if (!TextElementType.SYMBOL.equals(sentence.getElementType())) {
                replaceLexeme(sentence);
            }
        }
    }

    public int countSentenceWithRepeatWord(TextComponent component) {

        ArrayList<TextComponent> sentences = new ArrayList<>();
        Iterator sentenceIterator = sentences.iterator();
        int result = 0;
        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();
            ArrayList<TextComponent> words = findWords(sentence);
            result += hasRepeatWord(words)? 1 : 0;
        }
        return result;
    }

    public ArrayList<TextComponent> findSentence(TextComponent component) {
        ArrayList<TextComponent> result = new ArrayList<>();
        Iterator sentenceIterator = component.getChildren().iterator();
        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();

            if (TextElementType.SENTENCE.equals(sentence.getElementType())) {
                result.add(sentence);

            } else if (!TextElementType.SYMBOL.equals(sentence.getElementType())) {
                result.addAll(findSentence(sentence));
            }
        }
        return result;
    }

    private ArrayList<TextComponent> findWords(TextComponent sentence) {
        Iterator sentenceIterator = sentence.getChildren().iterator();
        ArrayList<TextComponent> result = new ArrayList<>();
        while (sentenceIterator.hasNext()) {
            TextComponent word = (TextComponent) sentenceIterator.next();

            if (TextElementType.WORD.equals(word.getElementType())) {
                result.add(word);

            } else if (!TextElementType.SYMBOL.equals(word.getElementType())) {
                result.addAll(findWords(word));
            }
        }
        return result;
    }

    private boolean hasRepeatWord(ArrayList<TextComponent> words) {
        Iterator iterator = words.iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            String word = ((TextComponent) iterator.next()).getElement().toLowerCase();
            Iterator iterator2 = words.iterator();
            int count = 0;
            while (iterator2.hasNext()) {
                String word2 = ((TextComponent) iterator2.next()).getElement().toLowerCase();
                count += word.equals(word2)? 1 : 0;
                if (count >= 2) {
                    return true;
                }
            }
        }
        return result;
    }


}
