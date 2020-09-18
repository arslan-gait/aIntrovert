package kz.edu.nu.cs.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@SequenceGenerator(name = "EventSeq", sequenceName = "Event_Seq", allocationSize=1)
@NamedQueries({
		@NamedQuery(name="Event.findAll", query="select e from Event e where e.isCompleted = false"),
		@NamedQuery(name="Event.getEventByAdmin", query="select e from Event e where e.admin = :email"),
		@NamedQuery(name="Event.getEventById", query="select e from Event e where e.id = :id"),
		@NamedQuery(name="Event.getNameById", query="select e.name from Event e where e.id =:id"),
		@NamedQuery(name="Event.getActiveEventsByParticipantEmail", query = "select e from Event e left outer join e.participants s where s.email=:email and e.isCompleted=false"),
		@NamedQuery(name="Event.getPassiveEventsByParticipantEmail", query = "select e from Event e left outer join e.participants s where s.email=:email and e.isCompleted=true"),
		@NamedQuery(name="Event.getEventsByParticipantId", query = "select e from Event e left outer join e.participants s where s.id=:id")
})
public class Event implements Serializable {

	private static final long serialVersionUID = 12252632212171L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventSeq")
	private int id;

	private String name;
	private String admin;
	private String description;
	private String img;
	private int maxsize;
	private int isactive = 0;
	private boolean isCompleted;

	@Transient
	private boolean amIParticipant;
	@Transient
	private int currentSize;



	@ManyToMany(targetEntity=User.class)
	//@JoinTable(name="EVENT_USER", joinColumns = @JoinColumn(name = "Event_ID"), inverseJoinColumns = @JoinColumn(name = "participants_ID"))
	private Set<User> participants;

	@Temporal(TemporalType.TIMESTAMP)
	private Date meetingdate;
	private String location;
	private int price;
	private int points;

	public Event() {
		participants = new HashSet<>();
		setAmIParticipant(false);
		setCompleted(false);
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + points;
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (points != other.points)
			return false;
		return price == other.price;
	}

	public Date getMeetingdate() {
		return meetingdate;
	}

	public void setMeetingdate(Date meetingdate) {
		this.meetingdate = meetingdate;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", name='" + name + '\'' +
				", admin='" + admin + '\'' +
				", description='" + description + '\'' +
				", img='" + img + '\'' +
				", maxsize=" + maxsize +
				", isactive=" + isactive +
				", participants=" + participants +
				", meetingdate=" + meetingdate +
				", location='" + location + '\'' +
				", price=" + price +
				", points=" + points +
				'}';
	}

	public int getMaxsize() {
		return maxsize;
	}

	public void setMaxsize(int maxsize) {
		this.maxsize = maxsize;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public boolean isAmIParticipant() {
		return amIParticipant;
	}

	public void setAmIParticipant(boolean amIParticipant) {
		this.amIParticipant = amIParticipant;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean completed) {
		isCompleted = completed;
	}
	public int getCurrentSize() {
		return participants.size();
	}
	public int setCurrentSize(int size) {
		return this.currentSize = size;
	}

}
