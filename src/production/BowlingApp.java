package production;

public class BowlingApp {
    private int[][] game;
    private int spare;
    private int frame;
    private int roll;
    private int strike;
    private int score;
    private String name;
    private int gutterBalls = 0;

    public BowlingApp(String n) {
        this.name = n;
        this.game = new int[14][2];
        this.strike = 0;
        this.spare = 0;
        this.frame = 1;
        this.roll = 0;
    }

    public boolean shot(int knockPins) {
        if (this.roll == 2) {
            System.out.println("Game over, you cannot roll");
            return false;
        } else {
            if (knockPins == 0) {
                ++this.gutterBalls;
            }

            if (knockPins >= 0 && knockPins <= 10) {
                if (this.game[10][0] == 10) {
                    if (this.roll == 0) {
                        if (knockPins == 10) {
                            ++this.strike;
                        }

                        this.game[11][0] = knockPins;
                        ++this.roll;
                        return false;
                    } else {
                        if (knockPins == 10) {
                            ++this.strike;
                        }

                        this.game[11][1] = knockPins;
                        ++this.roll;
                        System.out.println("Game Over");
                        return true;
                    }
                } else if (this.game[10][0] + this.game[10][1] == 10 && this.game[10][0] != 10) {
                    this.game[11][0] = knockPins;
                    System.out.println("Game over!");
                    ++this.roll;
                    ++this.roll;
                    return false;
                } else if (knockPins == 10 && this.roll == 0) {
                    this.game[this.frame][0] = 10;
                    ++this.frame;
                    ++this.strike;
                    return true;
                } else if (this.roll == 1) {
                    this.game[this.frame][this.roll] = knockPins;
                    if (this.frame == 10 && this.game[10][0] + this.game[10][1] != 10) {
                        System.out.println("Game over");
                        return false;
                    } else {
                        if (this.game[this.frame][0] + this.game[this.frame][1] == 10 && this.game[this.frame][0] != 10) {
                            ++this.spare;
                        }

                        --this.roll;
                        ++this.frame;
                        return true;
                    }
                } else {
                    try {
                        this.game[this.frame][this.roll] = knockPins;
                        ++this.roll;
                        return false;
                    } catch (Exception var3) {
                        System.out.println("Game finished! :)");
                        return true;
                    }
                }
            } else {
                System.out.println("Invalid number for pins, choose number between 0 and 10");
                return false;
            }
        }
    }

    public void printScore() {
        byte rows;
        byte columns;
        int i;
        int j;
        if (this.frame == 11) {
            rows = 12;
            columns = 2;

            for(i = 1; i < rows; ++i) {
                for(j = 0; j < columns; ++j) {
                    System.out.println(this.game[i][j] + " ");
                }

                System.out.println("");
            }
        } else {
            rows = 11;
            columns = 2;

            for(i = 1; i < rows; ++i) {
                for(j = 0; j < columns; ++j) {
                    System.out.println(this.game[i][j] + " ");
                }

                System.out.println("");
            }
        }

    }

    public int addScore() {
        int result = 0;
        boolean rows = true;
        int columns = 2;
        int currentFrame = this.frame;

        int i;
        for(i = currentFrame; i >= 0; --i) {
            for(int j = 0; j < columns; ++j) {
                result += this.game[i][j];
            }
        }

        for(i = currentFrame; i >= 0; --i) {
            if (i == 11) {
                if (this.game[10][0] == 10) {
                    result += this.game[11][0] + this.game[11][1];
                }

                if (this.game[10][0] + this.game[10][1] == 10 && this.game[10][0] != 10) {
                    result += this.game[11][0];
                }

                --i;
                --i;
            }

            if (this.game[i][0] == 10) {
                if (this.game[i + 1][0] != 10) {
                    result += this.game[i + 1][0] + this.game[i + 1][1];
                }

                if (this.game[i + 1][0] == 10) {
                    result += this.game[i + 1][0] + this.game[i + 2][0];
                }
            }

            if (this.game[i][0] + this.game[i][1] == 10 && this.game[i][0] != 10) {
                result += this.game[i + 1][0];
            }
        }

        this.score = result;
        System.out.println("Result: " + this.score);
        return this.score;
    }

    public int returnRoll() {
        return this.roll;
    }

    public int returnSpare() {
        return this.spare;
    }

    public int returnStrike() {
        return this.strike;
    }

    public String returnName() {
        return this.name;
    }

    public int returnGutterBalls() {
        return this.gutterBalls;
    }

    public int returnScore() {
        int currentScore = this.addScore();
        return currentScore;
    }

    public int returnFrame() {
        return this.frame;
    }

    public void updateInfo() {
        System.out.println(this.name + "'s frame " + this.frame + ": Score is " + this.returnScore() + ", Strikes " + this.returnStrike() + ", Spares " + this.returnSpare() + ", Gutter Balls " + this.returnGutterBalls());
    }
}
