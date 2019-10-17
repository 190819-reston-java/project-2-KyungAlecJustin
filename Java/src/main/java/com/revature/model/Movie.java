package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@Column(name="director")
	private String director;
	
	@Column(name = "plot")
	private String plot;
	
	@Column(name = "poster")
	private String poster;
	
	@Column(name = "released_date")
	private String released;
	
	
	//AT Mapping CODE-------------------------------------------------------------------------------
//	@ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
//	private List<Watchlist> watchlists = new ArrayList<Watchlist>();
	

	public Movie() {
		super();
	}

	public Movie(int movieId, String title, String director, String plot, String poster, String released) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.director = director;
		this.plot = plot;
		this.poster = poster;
		this.released = released;
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

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + movieId;
		result = prime * result + ((plot == null) ? 0 : plot.hashCode());
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((released == null) ? 0 : released.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie other = (Movie) obj;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (movieId != other.movieId)
			return false;
		if (plot == null) {
			if (other.plot != null)
				return false;
		} else if (!plot.equals(other.plot))
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (released == null) {
			if (other.released != null)
				return false;
		} else if (!released.equals(other.released))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", director=" + director + ", plot=" + plot
				+ ", poster=" + poster + ", released=" + released + "]";
	}
		
}
