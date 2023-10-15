package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoaderImpl implements MapLoader {
    @Override
    public Map load(String mapFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapFile));
            List<String> lines = new ArrayList<>();
            String line;
            int maxLineLength = 0;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                maxLineLength = Math.max(maxLineLength, line.length());
            }
            reader.close();

            String[][] representation = new String[lines.size()][maxLineLength];
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    representation[i][j] = String.valueOf(line.charAt(j));
                }
            }

            return new Map(representation, true);
        } catch (IOException e) {
            e.printStackTrace();
            return new Map(new String[0][0], false);
        }
    }
}
