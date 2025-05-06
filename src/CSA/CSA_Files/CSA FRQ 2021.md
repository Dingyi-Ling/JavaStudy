# CSA FRQ 2021 

## Question 1

```
public int scoreGuess(String guess){
	int count = 0;
	String temp = secret;
	while(true){
		if(temp.indexOf(guess) == -1){
			break;
		} else {
			count ++;
			if(temp.indexOf(guess) + 1  > temp.length() - 1){
				break;
			}
			temp = temp.substring(temp.indexOf(guess) + 1);
		}
	}
	count = count * guess.length() * guess.length();
	return count;
}
```

```
public String findBetterGuess(String guess1, String guess2){
	if(scoreGuess(guess1) > scoreGuess(guess2)){
		return guess1;
	} else if(scoreGuess(guess1) < scoreGuess(guess2)){
		return guess2;
	} else {
		if(guess1.compareTo(guess2) > 0){
			return guess1;
		} else {
			return guess2;
		}
	}
}
```

## Question 2

```

```

