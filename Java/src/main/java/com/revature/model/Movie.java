package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "movies")
@Component
public class Movie implements Serializable {

	private static final long serialVersionUID = 1482141467399248051L;
	
	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "actor")
	private String actor;
	
	@Column(name = "released_date")
	private String releasedDate;
	
	//AT Mapping CODE-------------------------------------------------------------------------------
	@ManyToMany
	@JoinTable(
			name = "watchlist_movie", 
			joinColumns = {@JoinColumn(name="movie_id")},
			inverseJoinColumns = {@JoinColumn(name = "watchlist_id")})
	private List<Watchlist> watchlists = new ArrayList<Watchlist>();
	
	//AT Mapping CODE-------------------------------------------------------------------------------

	
	
	public Movie() {
		super();
	}

	public Movie(int movieId, String title, String director, String actor, String releasedDate) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.releasedDate = releasedDate;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actor, director, movieId, releasedDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Movie)) {
			return false;
		}
		Movie other = (Movie) obj;
		return Objects.equals(actor, other.actor) && Objects.equals(director, other.director)
				&& movieId == other.movieId && Objects.equals(releasedDate, other.releasedDate)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", director=" + director + ", actor=" + actor
				+ ", releasedDate=" + releasedDate + "]";
	}

	
	
	

}
