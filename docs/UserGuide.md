---
layout: page
title: User Guide
---
Are you a CS2109S TA (Teaching Assistant) conducting multiple tutorials a week? Are you having troubles with keeping track of student details and their progress?

Introducing nerdTrackerPlus, your ultimate companion in managing student details and tracking their progress effortlessly!

nerdTrackerPlus is a **desktop app** specifically designed for **CS2109S tutors**. It helps you manage student details and progress for your tutorials. nerdTrackerPlus is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, nerdTrackerPlus can get your contact management tasks done faster than traditional GUI apps.

---

## Table of Contents

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `nerdTrackerPlus.jar` from [here](https://github.com/AY2324S2-CS2103T-F11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your nerdTrackerPlus.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar nerdTrackerPlus.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe m/A1234567A e/e1234567@u.nus.edu tl/johnthedoe` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

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

### 1. Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

<h4>Format:</h4> 

`help`


### 2. Adding a student : `add`

Adds a student to nerdTrackerPlus.

</h4>Format:</h4>

`add n/NAME m/MATRIC_NUMBER e/EMAIL tl/TELEGRAM_HANDLE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

<h4>Fields:</h4>

|       Field       | Prefix | Required | Requirements                                                                                                      |
|:-----------------:|:------:|:--------:|:------------------------------------------------------------------------------------------------------------------|
|      `NAME`       |  `n/`  |   YES    | Name of the student. It should only contain alphanumeric characters and spaces.                                   |
|  `MATRIC_NUMBER`  |  `m/`  |   YES    | Matriculation number of the student. It should start and end with a capital letter and have 7 digits in between.  |
|      `EMAIL`      |  `e/`  |   YES    | Email of the student. Email should match the format: `<local>@<domain>.<label>`.                                  |
| `TELEGRAM_HANDLE` | `tl/`  |   YES    | Telegram username of the student. It should only contain alphanumeric characters and spaces.                      |
|       `TAG`       |  `t/`  |    NO    | Tag(s) for the student. It should only contain alphanumeric characters and spaces.                                |

<h4>Notes:</h4>
* A student is uniquely identified by their `NAME`. This field is case-sensitive.
* You may add multiple tags to a student by specifying the `t/` prefix multiple times.

<h4>Example:</h4>

* `add n/John Doe m/A1234567Z e/johnd@u.nus.edu tl/johndoe01`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 3. Listing all students : `list`

Shows a list of all students in nerdTrackerPlus.

</h4>Format:</h4>

`list`

<h4>Example:</h4>

* `list`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 4. Editing a student : `edit`

Edits an existing student in nerdTrackerPlus.

<h4>Format:</h4>

`edit INDEX [n/NAME] [m/MATRIC_NUMBER] [e/EMAIL] [tl/TELEGRAM_HANDLE] [t/TAG]…​`

<h4>Fields:</h4>

|       Field       | Prefix | Required | Requirements                                                                                                              |
|:-----------------:|:------:|:--------:|:--------------------------------------------------------------------------------------------------------------------------|
|      `INDEX`      |  NIL   |   YES    | `INDEX` **must be a positive integer** 1, 2, 3 …​ <br>`INDEX` cannot be greater than the number of students in the list.  |
|      `NAME`       |  `n/`  |    NO    | Name of the student. It should only contain alphanumeric characters and spaces.                                           |
|  `MATRIC_NUMBER`  |  `m/`  |    NO    | Matriculation number of the student. It should start and end with a capital letter and have 7 digits in between.          |
|      `EMAIL`      |  `e/`  |    NO    | Email of the student. Email should match the format: `<local>@<domain>.<label>`.                                          |
| `TELEGRAM_HANDLE` | `tl/`  |    NO    | Telegram username of the student. It should only contain alphanumeric characters and spaces.                              |
|       `TAG`       |  `t/`  |    NO    | Tag(s) for the student. It should only contain alphanumeric characters and spaces.                                        |

<h4>Notes:</h4>

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without specifying any tags after it.

<h4>Example:</h4>

*  `edit 1 m/A1111111Z e/johndoe@gmail.com` Edits the matriculation number and email address of the first student to be `A1111111Z` and `johndoe@gmail.com` respectively.

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 5. Locating students by name : `find`

Finds students whose names contain any of the given keywords.

</h4>Format:</h4>

`find KEYWORD [MORE_KEYWORDS]`

<h4>Fields:</h4>

|      Field       | Prefix | Required | Requirements |
|:----------------:|:------:|:--------:|:-------------|
|    `KEYWORD`     |  NIL   |   YES    | NIL          |
| `MORE_KEYWORDS`  |  NIL   |    NO    | NIL          |

<h4>Notes:</h4>

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

<h4>Example:</h4>

* `find alex david` returns `Alex Yeoh`, `David Li`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 6. Deleting a student : `delete`

Deletes the specified student from nerdTrackerPlus.

<h4>Format</h4>

`delete INDEX`

<h4>Fields:</h4>

|      Field      | Prefix | Required | Requirements                                                                                                             |
|:---------------:|:------:|:--------:|:-------------------------------------------------------------------------------------------------------------------------|
|     `INDEX`     |  NIL   |   YES    | `INDEX` **must be a positive integer** 1, 2, 3 …​ <br>`INDEX` cannot be greater than the number of students in the list. |

<h4>Notes:</h4>

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.

<h4>Example:</h4>

* `list` followed by `delete 2` deletes the 2nd student in the address book.

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 7. Remove tag from a student : `removetag`

Deletes the specified tag from a student in nerdTrackerPlus.

<h4>Format:</h4>

`removetag INDEX [t/TAG]…`

<h4>Fields:</h4>

|  Field  | Prefix | Required | Requirements                                                                                                             |
|:-------:|:------:|:--------:|:-------------------------------------------------------------------------------------------------------------------------|
| `INDEX` |  NIL   |   YES    | `INDEX` **must be a positive integer** 1, 2, 3 …​ <br>`INDEX` cannot be greater than the number of students in the list. |
|  `TAG`  |  `t/`  |   YES    | Tag on a student. It should only contain alphanumeric characters and spaces. Tag must exist on the student.              |


* Removes tags of the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.

<h4>Example:</h4>

* `list` followed by `removetag 2 t/friend` Removes the friend tag from the 2nd student in the address book.

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 8. Remove tag(s) from all students : `removetagall`

Deletes the specified tag(s) from all students that have the tag(s).

<h4>Format:</h4>

`removetagall t/TAG(S)…​`

<h4>Fields:</h4>

|  Field   | Prefix | Required | Requirements                                                                                                       |
|:--------:|:------:|:--------:|:-------------------------------------------------------------------------------------------------------------------|
| `TAG(S)` |  `t/`  |   YES    | Tag on a student. It should only contain alphanumeric characters and spaces. Tag must exist on at least 1 student. |

<h4>Notes:</h4>

* More than 1 tag can be specified to be removed.

<h4>Example:</h4>
`removetagall t/G19Group1 t/family`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 9. Filtering by tags : `filter`

Filters all students with the specified tags.

<h4>Format</h4>

`filter [all/any] NUMBER_OF_TAGS TAG(S)…​`

<h4>Fields:</h4>

|   Field   | Prefix | Required | Requirements                                                |
|:---------:|:------:|:--------:|:------------------------------------------------------------|
| `all/any` |  NIL   |    NO    | Must be lower case.                                         |
| `TAG(S)`  |  NIL   |   YES    | Tag should only contain alphanumeric characters and spaces. |

<h4>Notes:</h4>

* If `filter all` is used and more than 1 tag is used to filter, only students that have all the tags will be shown.
* If `filter any` is used and more than 1 tag is used to filter, students that match any one of the tags will be shown.
* Tags are case in-sensitive.

<h4>Example:</h4>
* `filter all 2 G19Group1 G19Group2`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 10. Marking individual participation scores : `markp`

Marks the participation score of a specific student.

<h4>Format:</h4>

`markp INDEX WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
|    `INDEX`    |   NONE   |    YES     | INDEX **must be a positive integer** 1, 2, 3 …​ <br><br> INDEX cannot be greater than the number of students in the list. |
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Notes:</h4>

* Marks the participation score of the student at the specified `INDEX` in the specified `WEEK_NUMBER`
* The `INDEX` refers to the index number shown in the displayed student list.

<h4>Example:</h4>

* `markp 1 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 11. Un-marking individual participation scores : `unmarkp`

Un-marks the participation score of a specific student.

<h4>Format:</h4>

`unmarkp INDEX WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
|    `INDEX`    |   NONE   |    YES     | INDEX **must be a positive integer** 1, 2, 3 …​ <br><br> INDEX cannot be greater than the number of students in the list. |
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Notes:</h4>

* Un-marks the participation score of the student at the specified `INDEX` in the specified `WEEK_NUMBER`
* The `INDEX` refers to the index number shown in the displayed student list.

<h4>Example:</h4>

* `unmarkp 1 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**

### 12. Marking individual attendance scores : `marka`

Marks the attendance of a specific student.

<h4>Format:</h4>

`marka INDEX WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
|    `INDEX`    |   NONE   |    YES     | INDEX **must be a positive integer** 1, 2, 3 …​ <br><br> INDEX cannot be greater than the number of students in the list. |
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Notes:</h4>

* Marks the attendance of the student at the specified `INDEX` in the specified `WEEK_NUMBER`
* The `INDEX` refers to the index number shown in the displayed student list.

<h4>Example:</h4>

* `marka 1 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 13. Un-marking individual attendance scores : `unmarka`

Un-marks the attendance of a specific student.

<h4>Format:</h4>

`unmarka INDEX WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
|    `INDEX`    |   NONE   |    YES     | INDEX **must be a positive integer** 1, 2, 3 …​ <br><br> INDEX cannot be greater than the number of students in the list. |
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Notes:</h4>

* Un-marks the attendance of the student at the specified `INDEX` in the specified `WEEK_NUMBER`
* The `INDEX` refers to the index number shown in the displayed student list.

<h4>Example:</h4>

* `unmarka 1 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 14. Marking all participation scores : `markallp`

Marks the participation scores of all students in the filtered list for a given week.

<h4>Format:</h4>

`markallp WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Example:</h4>

* `markallp 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 15. Un-marking all participation scores : `unmarkallp`

Un-marks the participation scores of all students in the filtered list for a given week.

<h4>Format:</h4>

`umarkallp WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Example:</h4>

* `umarkallp 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 16. Marking all attendance scores : `markalla`

Marks the attendance of all students in the filtered list for a given week.

<h4>Format:</h4>

`markalla WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Example:</h4>

* `markalla 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 17. Un-marking all attendance scores : `unmarkalla`

Un-marks the attendance of all students in the filtered list for a given week.

<h4>Format:</h4>

`unmarkalla WEEK_NUMBER`

<h4>Fields:</h4>

|     Field     |  Prefix  |  Required  | Requirements                                                                                                              |
|:-------------:|:--------:|:----------:|:--------------------------------------------------------------------------------------------------------------------------|
| `WEEK_NUMBER` |   NONE   |    YES     | WEEK_NUMBER **must be a valid week from the range [3, 13].                                                                |

<h4>Example:</h4>

* `unmarkalla 3`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 18. Adding a custom assignment : `adda`

Adds assignment(s) to all users in the filtered list if they do not already exist.

<h4>Format:</h4>

`adda a/ASSIGNMENT…​`

<h4>Fields:</h4>

|      Field      | Prefix |  Required  | Requirements                                                           |
|:---------------:|:------:|:----------:|:-----------------------------------------------------------------------|
| `ASSIGNMENT...` |   a/   |    YES     | Assignment(s) should only contain alphanumeric characters and spaces.  |

<h4>Example:</h4>

* `adda a/assignment1 a/assignment2`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 19. Undoing a previous command : `undo`

Undoes the previous command that changed data.

<h4>Format:</h4>

`undo`

<h4>Notes:</h4>

* Only undoes a command if it has changed data in nerdTrackerPlus since launch.
* Will not undo if no commands were issued beforehand.

<h4>Example:</h4>

* `marka 1 3` to mark the attendance of student 1 in week 3, followed by `undo`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 20. Redoing a previously undone command : `redo`

Redoes the previously undone command.

<h4>Format:</h4>

`redo`

<h4>Notes:</h4>

* Will only redo the command that was undone immediately prior.
* Will not redo if no commands were undone beforehand.

<h4>Example:</h4>

* `marka 1 3` to mark the attendance of student 1 in week 3, followed by `undo`, then `redo`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 21. Clearing all entries : `clear`

Clears all entries from the address book.

<h4>Format:</h4>

`clear`

<h4>Example:</h4>

* `clear`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### 22. Exiting the program : `exit`

Exits the program.

<h4>Format:</h4>

`exit`

<h4>Example:</h4>

* `exit`

**TODO: INSERT SCREENSHOT OF EXPECTED OUTPUT**


### Saving the data

nerdTrackerPlus data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

nerdTrackerPlus data are saved automatically as a JSON file `[JAR file location]/data/nerdTrackerPlus.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, nerdTrackerPlus will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the nerdTrackerPlus to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous nerdTrackerPlus home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary

### Managing students

| Action                                                                          | Format                                                                           | Example                                                      |
|:--------------------------------------------------------------------------------|:---------------------------------------------------------------------------------|:-------------------------------------------------------------|
| [Add student](#2-adding-a-student--add)                                         | `add n/NAME m/MATRIC_NUMBER e/EMAIL tl/TELEGRAM_HANDLE [t/TAG]…​`                | `add n/John Doe m/A1234567Z e/johnd@u.nus.edu tl/johndoe01`  |
| [List students](#3-listing-all-students--list)                                  | `list`                                                                           | `list`                                                       |
| [Edit student](#4-editing-a-student--edit)                                      | `edit INDEX [n/NAME] [m/MATRIC_NUMBER] [e/EMAIL] [tl/TELEGRAM_HANDLE] [t/TAG]…​` | `edit 1 m/A1111111Z e/johndoe@gmail.com`                     |
| [Find student](#5-locating-students-by-name--find)                              | `find KEYWORD [MORE_KEYWORDS]`                                                   | `find alex david`                                            |
| [Delete student](#6-deleting-a-student--delete)                                 | `delete INDEX`                                                                   | `delete 2`                                                   |
| [Remove tag from student](#7-remove-tag-from-a-student--removetag)              | `removetag INDEX [t/TAG]…`                                                       | `removetag 2 t/friend`                                       |
| [Remove tag from all students](#8-remove-tags-from-all-students--removetagall)  | ` removetagall t/TAG(S)…​`                                                       | `removetagall t/G19Group1 t/family`                          |
| [Filter student](#9-filtering-by-tags--filter)                                  | `filter [all/any] NUMBER_OF_TAGS TAG(S)…​`                                       | `filter all 2 G19Group1 G19Group2`                           |

### Managing participation, attendance and assignments
| Action                                                                                     | Format                      | Example                            |
|:-------------------------------------------------------------------------------------------|:----------------------------|:-----------------------------------|
| [Mark individual participation](#10-marking-individual-participation-scores--markp)        | `markp INDEX WEEK_NUMBER`   | `markp 1 3`                        |
| [Unmark individual participation](#11-un-marking-individual-participation-scores--unmarkp) | `unmarkp INDEX WEEK_NUMBER` | `unmarkp 1 3`                      |
| [Mark individual attendance](#12-marking-individual-attendance-scores--marka)              | `marka INDEX WEEK_NUMBER`   | `marka 1 3`                        |
| [Unmark individual attendance](#13-un-marking-individual-attendance-scores--unmarka)       | `unmarka INDEX WEEK_NUMBER` | `unmarka 1 3`                      |
| [Mark all participation](#14-marking-all-participation-scores--markallp)                   | `markallp WEEK_NUMBER`      | `markallp 3`                       |
| [Unmark all participation](#15-un-marking-all-participation-scores--unmarkallp)            | `umarkallp WEEK_NUMBER`     | `umarkallp 3`                      |
| [Mark all attendance](#16-marking-all-attendance-scores--markalla)                         | `markalla WEEK_NUMBER`      | `markalla 3`                       |
| [Unmark all attendance](#17-un-marking-all-attendance-scores--unmarkalla)                  | `unmarkalla WEEK_NUMBER`    | `unmarkalla 3`                     |
| [Add assignment](#18-adding-a-custom-assignment--adda)                                     | `adda a/ASSIGNMENT…​`       | `adda a/assignment1 a/assignment2` |

### General

| Action                                                | Format   | Example |
|:------------------------------------------------------|:---------|:--------|
| [View Help](#1-viewing-help--help)                    | `help`   | `help`  |
| [Undo](#19-undoing-a-previous-command--undo)          | `undo`   | `undo`  |
| [Redo](#20-redoing-a-previously-undone-command--redo) | `redo`   | `redo`  |
| [Clear](#21-clearing-all-entries--clear)              | `clear`  | `clear` |
| [Exit](#22-exiting-the-program--exit)                 | `exit`   | `exit`  |
