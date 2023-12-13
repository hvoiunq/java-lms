package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionPlan {

    private EnrollmentStatus sessionStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPlan(EnrollmentStatus sessionStatus, LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("강의의 시작일자와 종료일자는 필수 정보입니다.");
        }
    }

    public EnrollmentStatus getSessionStatus() {
        return sessionStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}