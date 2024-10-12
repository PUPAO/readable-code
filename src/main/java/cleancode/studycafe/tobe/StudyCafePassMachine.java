package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
    public void run() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
        outputHandler.askPassTypeSelection();

        try {
            StudyCafePass selectedPass = selectedPass();
            Optional<StudyCafePass> optionalLockerPass = getStudyCafePass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                () -> outputHandler.showPassOrderSummary(selectedPass)
            );
            // HOURLY와 함께 메서드를 추출하지 않은 이유는 Fiexed만 통일성이 깨지기 때문

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectedPass() {
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        List<StudyCafePass> passCandidates = findPassCandidatesBy(passType);

        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> allPassList = studyCafeFileHandler.readStudyCafePasses();

        return allPassList.stream()
            .filter(passType -> passType.isSamePassType(studyCafePassType))
            .toList();
    }

    private Optional<StudyCafePass> getStudyCafePass(StudyCafePass selectedPass) {
        if(selectedPass.getPassType() != StudyCafePassType.FIXED) {
            return Optional.empty();
        }
        List<StudyCafePass> lockerPasseList = studyCafeFileHandler.readLockerPasses();

        StudyCafePass lockerPassCandidate = lockerPasseList.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);

        if (lockerPassCandidate != null) {
            outputHandler.askLockerPass(lockerPassCandidate);
            boolean isLockerSelected = inputHandler.getLockerSelection();

            if(isLockerSelected) return Optional.of(lockerPassCandidate);
        }

        return Optional.empty();
    }

}
