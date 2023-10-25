---
layout: page
title: User Guide
---

Foster Family is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Foster Family can get foster family management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `FosterFamily.jar` from [here](https://github.com/AY2324S1-CS2103T-T13-4/tp/releases/tag/v1.2.1).

3. Copy the file to the folder you want to use as the _home folder_ for your Foster Family Address Book.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar FosterFamily.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * `list` : Lists all fosterers.

  * `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/nil availability/nil animal/nil animalType/nil` : Adds a fosterer named `Jerry Tan` to the Foster Family Address Book.

  * `delete 3` : Deletes the 3rd fosterer shown in the current list.

  * `reset` : Deletes all fosterers.

  * `exit` : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

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

### Viewing Help for Commands : `help`

Shows the purpose and format of the different commands.

Format: `help COMMAND`

Parameter:
* `COMMAND`: One of the available commands

Examples:
* `help add` Displays the function and the format of how to use the `add` command

Expected output(success):
```agsl
Adds a fosterer to the address book.
Format: add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL_FOSTERED/WANTED t/TAG
```

Expected output (fail):
```agsl
Oops! There seems to be an error, please check the format of your command again.
```

### Adding a fosterer: `add`

Adds a fosterer to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL_FOSTERED/WANTED t/TAG…`

Parameters:
* `NAME`: Name of the fosterer
* `PHONE_NUMBER`: Phone number of the fosterer
* `EMAIL`: Email of the fosterer
* `ADDRESS`: Address of the foster family
* `HOUSING_TYPE` (case-sensitive) : HDB / Condo / Landed / nil
* `AVAILABILITY` (case-sensitive) : Available / NotAvailable / nil
* `ANIMAL_NAME`
  * `ANIMAL_NAME` (if `availability/NotAvailable`) : Name of animal fostered
  * `ANIMAL_NAME` (if `availability/nil`) : nil
  * `ANIMAL_NAME` (if `availability/Available`) : nil
* `TYPE_OF_ANIMAL_FOSTERED/WANTED` (case-sensitive) :
  * `TYPE_OF_ANIMAL_FOSTERED` (if `availability/NotAvailable`) : current.Dog / current.Cat / nil
  * `TYPE_OF_ANIMAL_WANTED` (if `availability/Available`) : able.Dog / able.Cat / nil
  * `TYPE_OF_ANIMAL_FOSTERED/WANTED` (if `availability/nil`) : nil

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
 A person can have any number of tags (including 0). ‘nil’ can be indicated for HOUSING_TYPE, AVAILABILITY, ANIMAL_NAME and TYPE_OF_ANIMAL_FOSTERED/WANTED if information is not currently available.
</div>

Valid cases:

| No. | Scenario                                                    | `AVAILABILITY` | `ANIMAL_TYPE`     | `ANIMAL_NAME` |
|-----|-------------------------------------------------------------|-------------|-------------------|---------------|
| 1   | Not fostering, insufficient info collected                  | `nil`         | `nil`             | `nil`         |
| 2   | Not fostering, insufficient info collected                  | `Available`   | `nil`             | `nil`         |
| 3   | Not fostering, preference indicated                         | `Available`   | `able.Dog/Cat`    | `nil`         |
| 4   | Not fostering (e.g. overseas, currently not able to foster) | `NotAvailable`            | `nil`             | `nil`         |
| 5   | Fostering: ALL information must be present                  | `NotAvailable`            | `current.Dog/Cat` | NOT `nil`          |

Examples:
* `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Cat t/Urgent`
adds a fosterer named Jerry Tan with the phone number 98765412 and email address jerry123@example.com; his address is Baker street, block 5, #27-01, housing type is HDB and he is fostering a cat named Dexter. An urgent visit is required.
* `add n/Tom Lee p/98123456 e/tom@example.com a/Happy street, block 123, #01-01 t/Available t/HDB t/able.Dog`
adds a fosterer named Pete Tay with the phone number 98765411 and email address pete@example.com; his address is Happy street, block 5, #01-01, housing type is Condo and currently he is not fostering any animal but looking to foster a cat.
* In the case where duplicates are given, last one will be chosen:
  * `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB housing/Condo availability/Available availability/NotAvailable animal/Dexter animal/Happy animalType/able.Dog animalType/current.Cat t/Urgent`
    * Outcome will be: `New fosterer added: Jerry Tan; Phone: 98765412; Email: jerry123@example.com; Address: Baker street, block 5, #27-01; Housing: Condo; Availability: NotAvailable; Animal name: Happy; Animal type: current.Cat; Tags: [Urgent]`

Expected output (success):
```agsl
New fosterer added: Jerry Tan; Phone: 98765412; Email: jerry123@example.com; Address: Baker street, block 5, #27-01; Housing: HDB; Availability: NotAvailable; Animal name: Dexter; Animal type: current.Cat; Tags: [Urgent]
```

Expected output (fail):

| Scenario                                                                                                                        | Error Message                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Without any of the following: `n/`, `p/`,`e/`, `a/`, `housing/`, `availability/`, `animal/`, `animalType/`                      | `Invalid command format! add: Adds a person to the address book. Parameters: n/NAME p/PHONE e/EMAIL a/ADDRESS housing/HOUSING availability/AVAILABILITY animal/ANIMAL_NAME animalType/ANIMAL_TYPE [t/TAG]... Note: If information for that field is not available, put 'nil'. Example: add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Dog t/Urgent t/goodWithDogs` |
| `availability/nil` but `animal/` is not 'nil'                                                                                   | `When an animal name is provided, availability should not be 'Available' or 'nil'.`                                                                                                                                                                                                                                                                                                                                                                                  |
| `availability/Available` but `animal/` is not 'nil'                                                                             | `When an animal name is provided, availability should not be 'Available' or 'nil'.`                                                                                                                                                                                                                                                                                                                                                                                  |
| `housing/` with values other than ‘HDB’, ‘Condo’, ‘Landed’, ‘nil’                                                               | `Housing type should be either 'HDB', 'Condo', 'Landed' or 'nil'`                                                                                                                                                                                                                                                                                                                                                                                                    |
| `availability/` with values other than ‘Available’, ‘NotAvailable’, ‘nil’                                                       | `Availability should be either 'Available', 'NotAvailable' or 'nil'`                                                                                                                                                                                                                                                                                                                                                                                                 |
| `availability/nil` but `animalType/` is not 'nil'                                                                               | `If fosterer is available, animal type should be 'able.Dog' / 'able.Cat'. If animal type information is not available, it should be inputted as 'nil'. If fosterer is NOT available and is currently fostering, animal type should be 'current.Dog' / 'current.Cat'. If fosterer is currently unable to foster, animal type should be inputted as 'nil'. If availability is 'nil', animal type should be 'nil' too.`                                                                                                                                                                   |
| `availability/Available` with `animalType/` values set to other values which are NOT ‘able.Cat’ or ‘able.Dog’ or 'nil'           | `If fosterer is available, animal type should be 'able.Dog' / 'able.Cat'. If animal type information is not available, it should be inputted as 'nil'. If fosterer is NOT available and is currently fostering, animal type should be 'current.Dog' / 'current.Cat'. If fosterer is currently unable to foster, animal type should be inputted as 'nil'. If availability is 'nil', animal type should be 'nil' too.`                                                                                                                                                                   |
| `availability/NotAvailable` with `animalType/` values set to other values which are NOT ‘current.Cat’ or ‘current.Dog' or 'nil' | `If fosterer is available, animal type should be 'able.Dog' / 'able.Cat'. If animal type information is not available, it should be inputted as 'nil'. If fosterer is NOT available and is currently fostering, animal type should be 'current.Dog' / 'current.Cat'. If fosterer is currently unable to foster, animal type should be inputted as 'nil'. If availability is 'nil', animal type should be 'nil' too.`                                                                                                                                                                     |
| `availability/NotAvailable` with `animalType/` values set to 'nil' but `animal/` values NOT 'nil'                               | `When availability is 'NotAvailable', animal name and type have to either be both 'nil' or both not 'nil'.`                                                                                                                                                                                                                                                                                                                                                          |
| `availability/NotAvailable` with `animal/` values set to 'nil' but `animalType/` values NOT 'nil'                               | `When availability is 'NotAvailable', animal name and type have to either be both 'nil' or both not 'nil'.`                                                                                                                                                                                                                                                                                                                                                          |

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

### Viewing a fosterer's detail : `view`

Views a fosterer's full details in the profile page. 

Format: `view INDEX`

Parameters:
* `INDEX`: Index of a fosterer to view their details shown in the list obtained by the find/list command

<div markdown="span" class="alert alert-primary">
  :exclamation: <b>Important:</b>
Some commands are not available while on profile view page. 
The list of available commands are: 
<ul>
  <li> <code>help</code> </li>
  <li> <code>edit</code> </li>
  <li> <code>exit</code> command to <b>exit the profile view page</b> </li>
</ul>
</div> 

Example:
* `list` followed by `view 2` to view the profile of the 2nd fosterer in the address book.

Expected output (success):
```agsl
Viewing Person: Jerry Tan; Phone: 98765412; Email: jerry123@example.com; Address: Baker street, block 5, #27-01; Housing: HDB; Availability: NotAvailable; Animal name: Dexter; Animal type: current.Cat; Tags: [Urgent]
```
Expected output (fail):
```agsl
Oops! Invalid fosterer index provided, please check again.
```
### Editing a fosterer's detail : `edit`

Edits or views the details of a fosterer stored in the address book.

Format 1: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [housing/HOUSING_TYPE] [availability/AVAILABILITY] [animal/ANIMAL_NAME] [animalType/TYPE_OF_ANIMAL_FOSTERED/WANTED] [t/TAG…]`

Format 2: `edit INDEX`

Parameters:
* `INDEX`: Index of a fosterer to be edited shown in the list obtained by the find/list command
* `NAME`: New name of the fosterer 
* `PHONE_NUMBER`: New phone number of the fosterer
* `EMAIL`: New email of the fosterer
* `ADDRESS`: New address of the foster family
* `HOUSING_TYPE` (case-sensitive) : HDB / Condo / Landed / nil
* `AVAILABILITY` (case-sensitive) : Available / NotAvailable / nil
* `ANIMAL_NAME`
  * `ANIMAL_NAME` (if `availability/NotAvailable`) : New name of animal fostered
  * `ANIMAL_NAME` (if `availability/nil`) : nil
  * `ANIMAL_NAME` (if `availability/Available`) : nil
* `TYPE_OF_ANIMAL_FOSTERED/WANTED` (case-sensitive) :
  * `TYPE_OF_ANIMAL_FOSTERED` (if `availability/NotAvailable`) : current.Dog / current.Cat / nil
  * `TYPE_OF_ANIMAL_WANTED` (if `availability/Available`) : able.Dog / able.Cat / nil
  * `TYPE_OF_ANIMAL_FOSTERED/WANTED` (if `availability/nil`) : nil

<div markdown="span" class="alert alert-primary">
  :bulb: <b>Tip:</b>
The index of the fosterer has to be provided, however the number of parameters to be edited can vary from zero to all fields.
</div> <br>
<div markdown="span" class="alert alert-primary">
  :exclamation: <b>Important:</b>
If the parameters are not provided, <b><code>edit INDEX</code> operates the same way as <code>view INDEX</code></b>, leading you to the profile page of the person at index <code>INDEX</code> in the addressbook. 
</div> 
<br>

Examples:
*  `list` followed by `edit 3 n/John` edits the name of the 3rd fosterer in the address book to John.
*  `list` followed by `edit 1 p/12345678 animal/Bob` edits the phone number and the pet name of the 1st fosterer in the address book to 12345678 and Bob respectively.
*  `list` followed by `edit 2` changes the view to the profile page of the 2nd fosterer in the address book since parameters are not provided.

Expected Output (success):
```
Foster family details successfully edited!
```

Expected Output (failure):
1. Compulsory fields are not filled in
```
Oops! Compulsory fields are not filled in!
```
2. System Error
```
Oops! There seems to be an error, please check your fields again.
```

### Deleting a fosterer : `delete`

Deletes the data entry at the index-th position of the currently displayed list.

Format: `delete INDEX [INDEX...]`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution: The index of a fosterer is not fixed.**

A fosterer's index is relative to the current list of fosterers you are handling.
</div>

Parameter: `INDEX [INDEX...]`
* Index of a fosterer is displayed in the list that is obtained by the `find`/`list` command.
* At least one index must be provided.
* Index must be a positive integer: 1, 2, 3, …

<div markdown="span" class="alert alert-primary">
  :bulb: <b>Tip: Work faster.</b>
Multiple indices are allowed for mass deletion, each index separated by a white space.
</div>

* Duplicate indices are ignored.
* Extraneous white spaces are also ignored.


Examples:
* `list` followed by `delete 2` deletes the 2nd fosterer in the address book
* `find Jerry` or `list Jerry`, followed by `delete 1`, deletes the 1st fosterer in the resulting list
* `list` followed by `delete 1 3 7` deletes the 1st, 3rd and 7th fosterers in the address book
* `list` followed by `delete 3 3 3 3` deletes the 3rd fosterer in the address book


Expected output (success):
`list` followed by `delete 1 3 7`
```agsl
3 fosterers deleted:
Alex Yeoh; Phone: 87438807; Email: alexyeoh@example.com; Address: Blk 30 Geylang Street 29, #06-40; Housing: HDB; Availability: Available; Animal name: nil; Animal type: able.Cat; Tags: [new], 
Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com; Address: Blk 11 Ang Mo Kio Street 74, #11-04; Housing: HDB; Availability: Available; Animal name: nil; Animal type: able.Dog; Tags: [new], 
Roy Balakrishnan; Phone: 92624417; Email: royb@example.com; Address: Blk 45 Aljunied Street 85, #11-31; Housing: Landed; Availability: Available; Animal name: nil; Animal type: able.Cat; Tags: [new]
```

Expected output (fail):

1. A non-positive index provided: `delete -1`
```agsl
Invalid command format! 
delete: Deletes fosterers identified by the index number used in the displayed person list.
Parameters: INDEX [INDEX...] (must be a positive integer)
Example: delete 1 2 3
```

2. An index exceeds the number of fosterers in the list: `delete 0 1 99` but the list only has 6 fosterers
```agsl
The fosterer index provided is invalid.
```

### Sorting fosterers: `sort`

Sorts list of fosterers alphabetically, by name.

Format: `sort`

Expected output:
```agsl
List sorted in alphabetical order of names
```

### Viewing Statistics : `stats`
Displays the requested statistic about the currently displayed list.

Format: `stats FIELD`

Parameters:`FIELD` 
* The requested field whose statistic is to be calculated.
* Valid fields that can be requested for are:
  * `avail` :  shows the statistics of fosterers who are available to foster, and the animals they can foster.
  * `current` : shows the statistics of fosterers who are currently fostering, and the animals they are fostering.
  * `housing` : shows the statistics of the various housing types.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution: Statistics are calculated based on the currently displayed list.**

If you performed a `find available` command before `stats avail`, the resulting statistic will show that 100% of listed fosterers are available. Hence, please ensure that the current list for fosterers is the desired list you want your statistic to be calculated from. 
</div>

Examples: 
* `list` followed by `stats avail` calculates the statistics of fosterers who are available, based on all fosterers in the address book.
* `find cat` followed by `stats avail` calculates the statistics of fosterers who are available, based on the fosterers who are either currently fostering a cat or are able to foster a cat. 
* `list` followed by `stats current` calculates the statistics of fosterers who are currently fostering, based on all fosterers in the address book.
* `find available dog` followed by `stats housing` calculates the statistics of the various housing types based on the fosterers who are available and are able to foster a dog.

Expected output (success):
1. `list` followed by `stats avail`
```agsl
6 out of 10 listed fosterers are available (60%)!
Out of those available, 
- 2 can foster dogs (33.3%)
- 3 can foster cats (50.0%)
- 1 unknown (16.7%)
```

2. `list` followed by `stats current`
```agsl
4 out of 10 listed fosterers are currently fostering (40%)!
Out of those fostering, 
- 1 fostering dogs (25.0%)
- 3 fostering cats (75.0%)
```

3. `list` followed by `stats housing`
```agsl
Out of 10 listed fosterers,
- 5 live in HDB (50.0%)
- 3 live in Condo (30.0%)
- 1 live in Landed (10.0%)
- 1 unknown (10.0%)
```

Expected output (fail):
1. Invalid command format: `stats`
```agsl
Invalid command format!
stats: Displays the requested statistic about the currently displayed list.
Parameters: FIELD (which can take the values avail, current or housing).
Examples: 
- stats avail 
- stats current
- stats housing
```

2. No fosterers in the list
```agsl
There are 0 fosterers listed. Please add some fosterers to view statistics.
```

### Clearing all entries : `reset`

Clears all entries from the address book.

Format: `reset`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

<div markdown="span" class="alert alert-primary">
  :bulb: <b>Tip:</b>
  As mentioned in the <code>view</code>, <code>exit</code> exits the program when executed on the normal foster family list view, while on the profile view page you are exited out of the page back to the list view. 
</div>

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

| Action     | Format, Examples                                                                                                                                                                                                                                                                                                                                      |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL_FOSTERED/WANTED [t/TAG]…` <br> e.g., `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Cat t/Urgent` |
| **Reset**  | `reset`                                                                                                                                                                                                                                                                                                                                               |
| **Delete** | `delete INDEX [INDEX...]`<br> e.g., `delete 1 2 3`                                                                                                                                                                                                                                                                                                    |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                                                                                                                                            |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                                                                            |
| **List**   | `list`                                                                                                                                                                                                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                |
| **Sort**   | `sort`                                                                                                                                                                                                                                                                                                                                                |
| **Stats**  | `stats FIELD` eg., `stats avail`, `stats current`  or `stats housing`                                                                                                                                                                                                                                                                                 |
