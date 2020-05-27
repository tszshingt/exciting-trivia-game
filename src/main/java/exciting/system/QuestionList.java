/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exciting.system;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Creates List of available questions
 * @author Leigh Chin
 */
public class QuestionList {
    
    /**
     * creates an iterator based on difficulty level
     * @param qs the question selector to determine if a question should be used
     * @return an iterator that iterates through the question list
     */
    public ListIterator<Question> getQuestionIterator(QuestionSelector qs) {
        return new
            ListIterator<Question>() {
                public boolean hasNext() {
                    return current < list.size();
               }
                
                public Question next() {
                    Question q = null;
                    
                    if (hasNext())
                        q = list.get(current++);
                    
                    while ((q != null) && (!qs.isValidQuestion(q))) {
                        if (hasNext())
                            q = list.get(current++);
                        else
                            q = null;
                    }
                    
                    return q;
                }
               
                public void remove() {
                    throw new UnsupportedOperationException();
                }
                
                public void reset() {
                    current = 0;
                }
                
                public void add(Question q) {
                    list.add(q);
                }
                
                public int previousIndex() {
                    throw new UnsupportedOperationException();
                }
                
                public int nextIndex() {
                    throw new UnsupportedOperationException();
                }
                
                public Question previous() {
                    throw new UnsupportedOperationException();
                }
 
                public boolean hasPrevious() {
                    throw new UnsupportedOperationException();
                }
 
                public void set(Question q) {
                    throw new UnsupportedOperationException();
                }
                
                private int current = 0;
            };     
    }
    
    /**
     * Add question to the list
     * @param q Question to add
     */
    public void addQuestion(Question q) {
        list.add(q);
    }
    
    private ArrayList<Question> list = new ArrayList<>();
}
