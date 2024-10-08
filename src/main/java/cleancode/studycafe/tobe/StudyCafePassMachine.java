package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void run() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
        outputHandler.askPassTypeSelection();

        try {
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            processPassBy(studyCafePassType, new StudyCafeFileHandler());

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void processPassBy(StudyCafePassType type, StudyCafeFileHandler studyCafeFileHandler) {
        // HOURLY와 함께 메서드를 추출하지 않은 이유는 Fiexed만 통일성이 깨지기 때문
        if (type == StudyCafePassType.HOURLY) {
            StudyCafePass selectedPass = selectStudyCafePassBy(StudyCafePassType.HOURLY, studyCafeFileHandler);
            outputHandler.showPassOrderSummary(selectedPass, null);
        }

        if (type == StudyCafePassType.WEEKLY) {
            StudyCafePass selectedPass = selectStudyCafePassBy(StudyCafePassType.WEEKLY, studyCafeFileHandler);
            outputHandler.showPassOrderSummary(selectedPass, null);
        }

        if (type == StudyCafePassType.FIXED) {
            List<StudyCafePass> lockerPasseList = studyCafeFileHandler.readLockerPasses();
            
            StudyCafePass selectedPass = selectStudyCafePassBy(StudyCafePassType.FIXED, studyCafeFileHandler);
            StudyCafePass lockerPass = findMatchingLockerPass(lockerPasseList, selectedPass);

            boolean lockerSelection = false;
            if (lockerPass != null) {
                outputHandler.askLockerPass(lockerPass);
                lockerSelection = inputHandler.getLockerSelection();
            }

            outputHandler.showPassOrderSummary(selectedPass, lockerSelection ? lockerPass : null);
        }
    }

    private static StudyCafePass findMatchingLockerPass(List<StudyCafePass> lockerPasseList, StudyCafePass selectedPass) {
        return lockerPasseList.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);
    }

    private StudyCafePass selectStudyCafePassBy(StudyCafePassType type, StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> studyCafePassList = studyCafeFileHandler.readStudyCafePasses();

        List<StudyCafePass> fixedPasseList = studyCafePassList.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == type)
            .toList();

        outputHandler.showPassListForSelection(fixedPasseList);

        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasseList);
        return selectedPass;
    }

}
