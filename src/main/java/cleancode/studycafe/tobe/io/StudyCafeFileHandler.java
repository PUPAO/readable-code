package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    public static final String LOCKER_CSV = "src/main/resources/cleancode/studycafe/locker.csv";
    public static final String PASS_LIST_CSV = "src/main/resources/cleancode/studycafe/pass-list.csv";

    public List<StudyCafePass> readStudyCafePasses() {
        return loadStudyCafePassesFrom(PASS_LIST_CSV);
    }

    public List<StudyCafePass> readLockerPasses() {
        return loadStudyCafePassesFrom(LOCKER_CSV);
    }

    private static List<StudyCafePass> loadStudyCafePassesFrom(String first) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(first));
            return parseStudyCafePasseList(lines);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private static List<StudyCafePass> parseStudyCafePasseList(List<String> lines) {
        List<StudyCafePass> studyCafePasseList = new ArrayList<>();

        for (String line : lines) {
            String[] values = line.split(",");
            double discountRate = 0;

            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);

            if(values.length == 4)
                discountRate = Double.parseDouble(values[3]);

            StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
            studyCafePasseList.add(studyCafePass);
        }

        return studyCafePasseList;
    }

}
