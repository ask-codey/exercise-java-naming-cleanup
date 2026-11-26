# Exercise: Clean Up the Bakery's Names (Java)

Exercise code for the lesson **Exercise: Clean Up the Bakery's Names (Java)** in Java Standards: Naming Conventions on [ask-codey.com](https://www.ask-codey.com).

Maple Street Bakery's order app works: it adds up an order and its 8% sales tax. But three people wrote it, and almost every name breaks Java's naming standard. You'll rename everything to the standard, using your editor's Rename so the app keeps working. Two checks tell you when you're done: the tests reach the code by its new names, and Checkstyle's naming checks look at every name.

## What's here

- `src/main/java/Bakery/`: the app, four files to finish. Every name in them is yours to rename (the table below lists each one).
  - `Order_Line.java`: one line of an order: a product, how many, the price of one.
  - `customerOrder.java`: one customer's order: its lines, the total with tax, paid or not.
  - `IOrderRepository.java`: where orders are saved.
  - `Main.java`: places one order and prints its total, so you can see the app still works.
- `src/test/java/`: the tests (`BakeryNamesTests.java`) and their helper (`Check.java`). You don't need to change these.
- `checkstyle.xml`: Checkstyle's naming checks, each with its default pattern. Ready to use.
- `pom.xml`, `mvnw`, `mvnw.cmd`, `.mvn/`: the Maven project, already set up to run the tests and Checkstyle. Ready to use.

## Getting started

1. In IntelliJ IDEA, choose **File > Open**, pick this folder, and let IntelliJ load the Maven project.
2. Run `Main` (the green arrow beside `main`). It prints `Maya owes $21.06`. Run it again after each rename: it should always print the same line.
3. Run the tests: right-click `src/test/java` and choose **Run 'All Tests'**, or run `./mvnw test` in Git Bash (`.\mvnw test` in PowerShell). Every test fails at first. That's expected: nothing has its standard name yet. The first run downloads Maven, so it takes a minute.

## What to do

**Rename with your editor, never by retyping.** Put the cursor on a name and press **Shift+F6** (Refactor > Rename). IntelliJ changes the name everywhere it's used, in every file, and renames a class's file with it.

1. **Move everything into the bakery's package.** A Java package is the company's domain reversed, all lowercase: maplestreetbakery.com becomes `com.maplestreetbakery.orders`. In the Project view, select the four files in `Bakery`, press **F6** (Refactor > Move), type `com.maplestreetbakery.orders` as the package, and let IntelliJ create it.
2. **Rename every name in the table**, file by file.

| File | Now | Rename to | The standard |
|---|---|---|---|
| `Order_Line.java` | class `Order_Line` | `OrderLine` | A class: a noun in UpperCamelCase |
| | variable `Product` | `product` | A variable: lowerCamelCase |
| | variable `qty` | `quantity` | Whole words, not abbreviations |
| | variable `UNIT_PRICE` | `unitPrice` | Capitals are only for `static final` constants |
| | parameters `product_name`, `Qty`, `unit_price` | `product`, `quantity`, `unitPrice` | Parameters: lowerCamelCase |
| | methods `Product()`, `get_qty()`, `GetUnitPrice()` | `getProduct()`, `getQuantity()`, `getUnitPrice()` | A getter: `get` and the variable's name |
| | method `Line_Total()` | `lineTotal()` | A method: lowerCamelCase |
| `customerOrder.java` | class `customerOrder` | `CustomerOrder` | A class: UpperCamelCase |
| | constant `salesTaxRate` | `SALES_TAX_RATE` | A `static final` constant: UPPER_SNAKE_CASE |
| | variables `customer_name`, `Lines`, `PAID` | `customerName`, `lines`, `paid` | Variables: lowerCamelCase |
| | parameter `CustomerName` | `customerName` | Parameters: lowerCamelCase |
| | methods `getCustomer_Name()`, `Add_Line()`, `CalcTot()` | `getCustomerName()`, `addLine()`, `calculateTotal()` | Methods: verbs in lowerCamelCase, whole words |
| | parameter `l` (in `addLine` and the loop) | `line` | Whole words: one letter only for a throwaway loop counter |
| | local variable `Subtotal` | `subtotal` | Local variables: lowerCamelCase |
| | method `Paid()` | `isPaid()` | A true-or-false getter: `is` and the name |
| | method `markPaid()` | keep it | Already right |
| `IOrderRepository.java` | interface `IOrderRepository` | `OrderRepository` | An interface: like a class, with no `I` in front |
| | methods `Save()`, `FIND_ALL()` | `save()`, `findAll()` | Methods: lowerCamelCase |
| | parameter `o` | `order` | Whole words |
| `Main.java` | local variables `Order1`, `TOTAL` | `order`, `total` | Local variables: lowerCamelCase |

## Check your work

Run the tests as often as you like:

```bash
./mvnw test
```

Each test turns green when its names are right, and its message says which name it's still looking for, such as *CustomerOrder needs a public method named calculateTotal(). Rename the method that does this job.*

When every test is green, run the whole check, the tests and then Checkstyle:

```bash
./mvnw verify
```

Checkstyle lists every name that still breaks the standard, with its file, line and the check that caught it, such as *Name 'CalcTot' must match pattern '^[a-z][a-zA-Z0-9]*$'. [MethodName]*. You're done when it ends with **BUILD SUCCESS**.

In PowerShell, write `.\mvnw` instead of `./mvnw`.

## The rules

- Rename only: don't change what any method does. `Main` prints `Maya owes $21.06` before and after.
- Don't change the tests, `checkstyle.xml` or `pom.xml`.
- Some names in the table, such as the parameters `l` and `o`, pass Checkstyle as they are. Rename them anyway: the standard asks for whole words, and a pattern can't check that.

## Tips

- Start with the package (step 1). Until it's done, every test says there's no class in `com.maplestreetbakery.orders` yet.
- Rename a class from its name in the code (Shift+F6 on `Order_Line`), and IntelliJ renames its file too. A public class must sit in a file of the same name.
- After each file, run `Main` and the tests. If something stops compiling, **Edit > Undo** reverses the last rename.

## Hand it in

When it works, push your solution to a public repo of your own on GitHub, then paste that repo's address into **Submit your exercise** on the lesson page at [ask-codey.com](https://www.ask-codey.com). That's what completes the lesson. The lesson page shows the Git commands, step by step.
