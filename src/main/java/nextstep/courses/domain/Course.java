package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Sessions;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Sessions sessions;

    private Long creatorId;

    private SystemTimeStamp systemTimeStamp;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new SystemTimeStamp(LocalDateTime.now(), null));
    }

    public Course(Long id, String title, Long creatorId, SystemTimeStamp systemTimeStamp) {
        this.id = id;
        this.title = title;
        this.sessions = Sessions.initialize();
        this.creatorId = creatorId;
        this.systemTimeStamp = systemTimeStamp;
    }

    public void addSession(Session session) {
        session.toCourse(this);
        sessions.addSession(session);
    }

    public String getTitle() {
        return title;
    }

    public Sessions getSessions() {
        return sessions;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return systemTimeStamp.getCreatedAt();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + systemTimeStamp.getCreatedAt() +
                ", updatedAt=" + systemTimeStamp.getUpdatedAt() +
                '}';
    }
}
