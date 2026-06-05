package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Result;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultManager {
    private static final String FILE_PATH = "results.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void save(Result result) {
        List<Result> results = loadAll();
        results.add(result);

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(results, writer);
        } catch (IOException e) {
            System.err.println("Failed to save result: " + e.getMessage());
        }
    }

    public List<Result> loadAll() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Result>>(){}.getType();
            List<Result> results = gson.fromJson(reader, listType);
            return results != null ? results : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Failed to load results: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Result> getTopResults(int amount) {
        List<Result> results = loadAll();
        results.sort(Comparator.comparingLong(Result::getTime));

        List<Result> topResults = new ArrayList<>();
        int minAmount = Math.min(results.size(), amount);

        for (int i = 0; i < minAmount; i++) {
            topResults.add(results.get(i));
        }

        return topResults;

    }
}
