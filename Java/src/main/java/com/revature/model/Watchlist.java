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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "watchlist")
@Component
public class Watchlist implements Serializable {

	private static final long serialVersionUID = 5508595899459911621L;

	@Id
	@Column(name = "watchlist_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int watchlistId;
	
	@Column(name = "watchlist_name")
	private String watchlistName;
	
	@Column(name = "owner_id")
	private int ownerId;
	
//	@Column(name = "movie")
//	private int movie;
	
	//AT Mapping CODE-------------------------------------------------------------------------------
	@OneToMany(mappedBy = "watchlist")
	private List<Movie> movies; //Lists movies in Watchlist
	
	@ManyToMany
	@JoinTable(
			name = "watchlist_movie", 
			joinColumns = {@JoinColumn(name="watchlist_id")},
			inverseJoinColumns = {@JoinColumn(name = "movie_id")})
	private List<Movie> moviesWatchlist = new ArrayList<Movie>();
	
	//AT Mapping CODE-------------------------------------------------------------------------------

	public Watchlist() {
		super();
	}

	public Watchlist(int watchlistId, String watchlistName, int ownerId, int movie) {
		super();
		this.watchlistId = watchlistId;
		this.watchlistName = watchlistName;
		this.ownerId = ownerId;
//		this.movie = movie;
	}

	public int getWatchlistId() {
		return watchlistId;
	}

	public void setWatchlistId(int watchlistId) {
		this.watchlistId = watchlistId;
	}

	public String getWatchlistName() {
		return watchlistName;
	}

	public void setWatchlistName(String watchlistName) {
		this.watchlistName = watchlistName;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

//	public int getMovie() {
//		return movie;
//	}
//
//	public void setMovie(int movie) {
//		this.movie = movie;
//	}
	
	

	

	@Override
	public String toString() {
		return "Watchlist [watchlistId=" + watchlistId + ", watchlistName=" + watchlistName + ", ownerId=" + ownerId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movies == null) ? 0 : movies.hashCode());
		result = prime * result + ((moviesWatchlist == null) ? 0 : moviesWatchlist.hashCode());
		result = prime * result + ownerId;
		result = prime * result + watchlistId;
		result = prime * result + ((watchlistName == null) ? 0 : watchlistName.hashCode());
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
		Watchlist other = (Watchlist) obj;
		if (movies == null) {
			if (other.movies != null)
				return false;
		} else if (!movies.equals(other.movies))
			return false;
		if (moviesWatchlist == null) {
			if (other.moviesWatchlist != null)
				return false;
		} else if (!moviesWatchlist.equals(other.moviesWatchlist))
			return false;
		if (ownerId != other.ownerId)
			return false;
		if (watchlistId != other.watchlistId)
			return false;
		if (watchlistName == null) {
			if (other.watchlistName != null)
				return false;
		} else if (!watchlistName.equals(other.watchlistName))
			return false;
		return true;
	}

	
}
