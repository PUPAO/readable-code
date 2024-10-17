//package cleancode.studycafe.tobe.model;
//
//
///**
// * StudyCafeLockerPass는 지금 클래스와 겹치는 부분이 많아서 삭제했습니다.
// *
// */
//public class StudyCafePass {
//
//    private final StudyCafePassType passType;
//    private final int duration;
//    private final int price;
//    private final double discountRate;
//
//    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
//        this.passType = passType;
//        this.duration = duration;
//        this.price = price;
//        this.discountRate = discountRate;
//    }
//
//    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
//        return new StudyCafePass(passType, duration, price, discountRate);
//    }
//
//    public boolean isSamePassType(StudyCafePassType passType) {
//        return this.passType != passType;
//    }
//
//    public StudyCafePassType getPassType() {
//        return passType;
//    }
//
//    public int getDuration() {
//        return duration;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public double getDiscountRate() {
//        return discountRate;
//    }
//
//    public String display() {
//        if (passType == StudyCafePassType.HOURLY) {
//            return String.format("%s시간권 - %d원", duration, price);
//        }
//        if (passType == StudyCafePassType.WEEKLY) {
//            return String.format("%s주권 - %d원", duration, price);
//        }
//        if (passType == StudyCafePassType.FIXED) {
//            return String.format("%s주권 - %d원", duration, price);
//        }
//        return "";
//    }
//}

package cleancode.studycafe.tobe.model.pass;

public interface StudyCafePass {

    StudyCafePassType getPassType();

    int getDuration();

    int getPrice();

}
