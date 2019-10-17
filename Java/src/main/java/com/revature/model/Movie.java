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
	private String releasedDate;
	
	//AT Mapping CODE-------------------------------------------------------------------------------
//	@ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
//	private List<Watchlist> watchlists = new ArrayList<Watchlist>();

	public Movie() {
		super();
	}
	
	//Constructor without watchlist
	public Movie(int movieId, String title, String plot, String releasedDate) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.plot = plot;
		this.releasedDate = releasedDate;
	}
	
	public Movie(int movieId, String title, String plot, String releasedDate, List<Watchlist> watchlists) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.plot = plot;
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

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieId;
		result = prime * result + ((plot == null) ? 0 : plot.hashCode());
		result = prime * result + ((releasedDate == null) ? 0 : releasedDate.hashCode());
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
		if (movieId != other.movieId)
			return false;
		if (plot == null) {
			if (other.plot != null)
				return false;
		} else if (!plot.equals(other.plot))
			return false;
		if (releasedDate == null) {
			if (other.releasedDate != null)
				return false;
		} else if (!releasedDate.equals(other.releasedDate))
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
		return "Movie [movieId=" + movieId + ", title=" + title + ", plot=" + plot + ", releasedDate=" + releasedDate
				+ ", watchlists=" + "]";
	}

	// AT Mapping
	// CODE-------------------------------------------------------------------------------

}
