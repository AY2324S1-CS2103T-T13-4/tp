---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a fosterer to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS animal/ANIMAL_NAME t/AVAILABILITY t/HOUSING_TYPE t/TYPE_OF_ANIMAL_FOSTERED/WANTED…​`

Parameters:
* `NAME`: Name of fosterer
* `PHONE_NUMBER`: Phone number of fosterer
* `EMAIL`: Email of fosterer
* `ADDRESS`: Address of foster family
* `ANIMAL_NAME`: Name of animal fostered (if fosterer is currently fostering an animal / “NotAvailable”)
* `AVAILABILITY`: Available / NotAvailable
* `HOUSING_TYPE`: HDB / Condo / Landed
* `TYPE_OF_ANIMAL_FOSTERED` (if not available): current.Dog / current.Cat
* `TYPE_OF_ANIMAL_WANTED` (if available): able.Dog / able.Cat

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags. Mandatory Tags: Availability, housing type and type of animal fostered/wanted. ANIMAL_NAME is optional, depending on whether the fosterer is currently fostering an animal.
</div>

Examples:
* `add n/Jerry Tan p/98765432 e/jerry123@example.com a/Baker street, block 5, #27-01 animal/Dexter t/NotAvailable t/Condo t/current.Cat t/Urgent`
adds a fosterer named Jerry Tan with the phone number 98765432 and email address jerry123@example.com; his address is Baker street, block 5, #27-01, housing type is condominium and he is fostering a cat named Dexter. An urgent visit is required.
* `add n/Tom Lee p/98123456 e/tom@example.com a/Happy street, block 123, #01-01 t/Available t/HDB t/able.Dog`
adds a fosterer named Tom Lee with the phone number 98123456 and email address tom@example.com; his address is Happy street, block 123, #01-01, housing type is HDB and currently he is not fostering any animal but looking to foster a dog.

Expected output (success):
```agsl
Fosterer Jerry Tan is successfully added!
```

Expected output (fail):
```agsl
Oops! There seems to be an error, please check the format of your command again.
```

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Listing fosterers: `list`

Lists fosterers that match a particular description or search, or all fosterers if the search is blank.

Format: `list *KEYWORDS`
Alias: `find`

* The keywords are case-insensitive.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* All fields are searched (including tags).
* Keywords can match as parts of words. e.g. `john` will match `Johnny`.
* Keywords can overlap. e.g. `samm my` will match `Sammy`
* Fosters must match all keywords (i.e. `AND` search).
  e.g. `Hans Bo` will return `Hansbo Grahm`, but not `Hans Duo`

Examples:
* `list` lists all fosterers in the address book
* `list john doe` matches "John Doe", "Doe John", "Johnny Doe", and "Mary" who lives on "John Doe Street"
* `list john john doe` is redundant and gives the same result as `list john doe`

Expected output (success):
```agsl
Fosterers matching query are listed.
```
UI also updates with a list of fosterers matching the query.

Expected output (fail):
```agsl
Oops! Invalid search expression, please check again.
```

### Deleting a fosterer : `delete`

Deletes the data entry at the index-th position of the currently displayed list.

Format: `delete INDEX...`

Parameter: `INDEX...`
* Index of a fosterer is shown in the list obtained by the `find/list` command.
* Must be a positive integer 1, 2, 3, …
* Multiple indexes are allowed for mass deletion,  each index separated by a white space.
* `delete` without an index is valid if and only if there exists only one data entry in the current list.

Examples:
* `list` followed by `delete 2` deletes the 2nd fosterer in the address book
* `find Jerry` followed by `delete 1` deletes the 1st fosterer in the results of the find command
* `list` followed by `delete 1 3 7` deletes the 1st, 3rd and 7th fosterers in the address book

Expected output (success):
```agsl
Fosterers Jerry Chee, John Doe, and Mary Ann are successfully deleted!
```

Expected output (fail):
```agsl
Oops! Invalid fosterer index provided, please check again.
```
### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                     |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                              |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                  |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                           |
| **List**   | `list`                                                                                                                                                               |
| **Help**   | `help`                                                                                                                                                               |
