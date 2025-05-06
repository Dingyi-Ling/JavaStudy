# CSA FRQ 2022

## Question 1

```
public int getScore(){
	int score = 0;
	if(levelOne.goalReached()){
		if(levelTwo.goalReached()){
			if(levelThree.goalReached()){
				if(isBonus()){
					score = (levelOne.getPoints() + levelTwo.getPoints() + levelThree.getPoints()) * 3;
					return score;
				} else {
					score = levelOne.getPoints() + levelTwo.getPoints() + levelThree.getPoints();
					return score;
				}
			} else {
				if(isBonus()){
					score = (levelOne.getPoints() + levelTwo.getPoints()) * 3;
					return score;
				} else {
					score = levelOne.getPoints() + levelTwo.getPoints();
					return score;
				}
			}
		} else {
			if(isBonus()){
					score = levelOne.getPoints() * 3;
					return score;
				} else {
					score = levelOne.getPoints();
					return score;
				}
		}
	}
	return score;
}
```

```
public int playManyTimes(int num){
	int max = Integer.MIN_VALUE;
	int temp = 0;
	for(int i = 0; i < num; i++){
		play();
		temp = getScore();
		if(temp > max){
			max = temp;
		}
	}
	return max;
}
```

## Question 2

```
class Textbook extends Book{
	private int editionNumber;
	public Textbook(String title, double price, int editionNumber){
		super(title, price);
		this.editionNumber = editionNumber;
	}
	public String getBookInfo(){
		return super.getBookInfo() + "-" + editionNumber;
	}
	public int getEdition(){
		return editionNumber;
	}
	public boolean canSubstituteFor(Textbook substitute){
		String title1 = super.getTitle();
		String title2 = substitute.getTitle();
		if(title1.equals(title2) && substitute.getEdition() <= this.getEdition()){
			return true;
		}
		return false;
	}
	
} 
```

## Question 3

```
public double getAverageRating(){
	double average = 0.0;
	double sum = 0.0;
	double length = (double)(allReviews.length);
	for(int i = 0; i < length; i++){
		sum += allReviews[i].getRating();
	}
	average = sum/length;
	return average;
}
```

```
public ArrayList<String> collectComments(){
	ArrayList<String> comments = new ArrayList<>();
	String temp = "";
	char lastCharacter = "";
	for(int i = 0; i < allReviews.length; i++){
		temp = i + "-" + allReviews[i].getComment();
		lastCharacter = temp.charAt(temp.length() - 1);
		if(lastCharacter != '!' && lastCharacter != '.'){
			temp = temp + ".";
		}
		comments.add(temp);
	}
	return comments;
}
```

## Question 4

```
public void repopulate(){
	int temp = 0;
	for(int row = 0; row < grid.length; row++){
		for(int col = 0; col < grid[row].length; col++){
			while(true){
				temp = (int)(Math.random() * MAX + 1);
				if(temp % 10 == 0 && temp % 100 != 0){
					grid[row][col] = temp;
					break;
				}
			}
		}
	}
}
```

