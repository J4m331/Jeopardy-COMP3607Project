package Jeopardy;

import Jeopardy.CSVInput;

import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Category> categories = Jeopardy.CSVInput.createCategories("src/main/java/Jeopardy/sample_game_CSV.csv");

        for(Category c:categories){
            System.out.println(c.getName());
            for(Question q:c.getQuestions()){
                System.out.println(q.getQuestion());
                System.out.println(q.getScore());
                for(Option o:q.getOptions()){
                    System.out.println(o.getHeader());
                    System.out.println(o.getContent());
                }
                System.out.println(q.getCorrectAnswer());
            }
        }
    }
}
