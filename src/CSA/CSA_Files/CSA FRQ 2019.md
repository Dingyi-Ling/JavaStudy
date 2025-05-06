# CSA FRQ 2019

## Question 1

```
public static int numberOfLeapYears(int year1, int year2){
	int count = 0;
	for(int i = year1; i <= year2; i++){
		if(isLeapYear(i)){
			count++;
		}
	}
	return count;
}
```

```
public static int dayOfWeek(int month, int day, int year){
	int shift = firstDayOfYear(year) - 1;
	int today = dayOfYear(month, day, year);
	int dayWeek = ((today % 7) + shift) % 7;
	return dayWeek;
}
```

## Question 2

```

```

