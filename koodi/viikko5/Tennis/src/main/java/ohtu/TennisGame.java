package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1++;
        else
            m_score2++;
    }

    public String getScore() {
        String score;

        if (m_score1 == m_score2) {
            score = scoresEven();
        }
        else if (m_score1>=4 || m_score2>=4) {
            score = anotherOneLeadingWithOverThreeBallsWon();
        }
        else {
            score = getPlayersScoreWhenMaxThree(m_score1) + "-" + getPlayersScoreWhenMaxThree(m_score2);
        }

        return score;
    }

    public String getPlayersScoreWhenMaxThree(int ballsWon) {
        String[] specialScoreTypes = {"Love", "Fifteen", "Thirty", "Forty"};
        return specialScoreTypes[ballsWon];
    }

    public String scoresEven() {
        String score = "Deuce";
        String[] specialDeuceTypes = {"Love-All", "Fifteen-All", "Thirty-All", "Forty-All"};

        if(m_score1 < 4) {
            score = specialDeuceTypes[m_score1];
        }
        return score;
    }

    public String anotherOneLeadingWithOverThreeBallsWon() {
        String score;
        int difference = m_score1-m_score2;

        if (difference==1) score = "Advantage player1";
        else if (difference ==-1) score = "Advantage player2";
        else if (difference>=2) score = "Win for player1";
        else score = "Win for player2";

        return score;
    }
}