# Vinted discount module

Vinted homework for iOS Junior developer position written in Java.

Design decisions
----------------------------
For the modification ease of existing rules and adding new ones, **Rules design pattern** was implemented. Each rule has its corresponding class (for example, ```SmallPackageRule.java```, ```FreeXShipmentRule.java``` and so on). So, if we want to add a new one, we just create a new Class and implement DiscountRule interface. Same goes for modification, for example, we want to modify the rule, where all small packages should match the lowest possible price - we just go to the SmallPackageRule class and do our modifications there. All of the rules, including the interface are in the ```rules``` package.

After we create all the rules we need, we should add them in the ```DiscountModule``` class by writing ```rules.add(new RuleName(moduleHelper));```. Thats it - discount module Class will iterate through the list of rules, applying discounts based on the current rule.

How to start it?
----------------------------
1. Download ```vinted.jar``` from the Git repository
2. Open command line window and navigate to the .jar file
3. Type ```java -jar vinted.jar``` and the module should start

Please make it sure that you have both input files in the same directory as the .jar file:
- User transactions should be listed in the ```input.txt``` file
- Provider list should be listed in the ```providers.txt``` file

Also, there is no check for data validity when loading providers from the file, so please make it sure that it satisfies this format:
```Name Size Price```, for example ```LP L 4.50```

# Output
----------------------------
Final transaction list with prices and discounts will appear in the ```outputTransactions.txt``` file.
