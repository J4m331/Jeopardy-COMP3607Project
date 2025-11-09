package Jeopardy;

import Jeopardy.CSVInput;

import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Category> categories = Jeopardy.CSVInput.createCategories("C:\\Users\\J4m4e\\Desktop\\COMP3607\\Project\\Jeopardy\\src\\main\\java\\Jeopardy\\sample_game_CSV.csv");
    }
}
