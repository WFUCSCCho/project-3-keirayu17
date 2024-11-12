/**
 * @file: Movie.java
 * @description: This class represents a movie object that has attributes such as
 *               rank, title, theaters, total gross, release date, and distributor.
 *               Fundamental methods are included.
 * @author: Keira Yu
 * @date: October 24, 2024
 */
public class Movie implements Comparable<Movie>{
    private String title;
    private int totalGross;
    private String releaseDate;
    private String distributor;

    // Default constructor
    public Movie(){
        this.title = "";
        this.totalGross = 0;
        this.releaseDate = "";
        this.distributor = "";
    }

    // Parametrized constructor
    public Movie(String title, int totalGross, String releaseDate, String distributor) {
        this.title = title;
        this.totalGross = totalGross;
        this.releaseDate = releaseDate;
        this.distributor = distributor;
    }

    // Copy constructor
    public Movie(Movie other) {
        this.title = other.title;
        this.totalGross = other.totalGross;
        this.releaseDate = other.releaseDate;
        this.distributor = other.distributor;
    }

    @Override
    public String toString(){
        return title + " | Gross: $" + totalGross + " | Release: " + releaseDate + " | Distributor: " + distributor + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return totalGross == movie.totalGross &&
                title.equals(movie.title) &&
                releaseDate.equals(movie.releaseDate) &&
                distributor.equals(movie.distributor);
    }

    @Override
    public int compareTo(Movie other) {
        // Compares by total gross
        return Integer.compare(other.totalGross, this.totalGross);
    }

    public String getTitle() {
        return title;
    }
    public long getTotalGross(){
        return totalGross;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getDistributor() {
        return distributor;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setTotalGross(int totalGross){
        this.totalGross = totalGross;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public void setDistributor(String distributor){
        this.distributor = distributor;
    }


}
