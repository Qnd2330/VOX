package VOX_Giat_La.Entity.DaXong;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name ="WorkSchedule")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleID;

    @Column
    private User user;
    @Column
    private Date workDate;
    @Column
    private Time startTime;
    @Column
    private Time endTime;
    @Column
    private String shiftType;

    public WorkSchedule(int scheduleID, User user, Date workDate, Time startTime, Time endTime, String shiftType) {
        this.scheduleID = scheduleID;
        this.user = user;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }
}
