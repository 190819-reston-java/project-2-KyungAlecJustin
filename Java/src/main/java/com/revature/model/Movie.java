package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movies")
@Proxy(lazy = false)
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
	
	//fetch = FetchType.LAZY
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JsonIgnore
	@NonNull
	@JoinColumn(name="watchlist_id")
	private Watchlist watchlist;
	
	
	//AT Mapping CODE-------------------------------------------------------------------------------
//	@ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
//	private Set<Watchlist> watchlists = new HashSet<Watchlist>();
//	

	public Movie() {
		super();
	}

	public Movie(int movieId, String title, String director, String plot, String poster, String released,
			Watchlist watchlist) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.director = director;
		this.plot = plot;
		this.poster = poster;
		this.released = released;
		this.watchlist = watchlist;
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

	public Watchlist getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(Watchlist watchlist) {
		this.watchlist = watchlist;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", director=" + director + ", plot=" + plot
				+ ", poster=" + poster + ", released=" + released + ", watchlist=" + watchlist + "]";
	}

	
	
		
}
