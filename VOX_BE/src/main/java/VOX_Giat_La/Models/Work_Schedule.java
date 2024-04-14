package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Work_Schedule")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work_Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;
    @Column(name = "workDate")
    private Date workDate;
    @Column(name = "startTime")
    private Time startTime;
    @Column(name = "endTime")
    private Time endTime;
    @Column(name = "shiftType")
    private String shiftType;
}
