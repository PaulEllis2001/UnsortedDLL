****************
* Double Linked List
* CS 221 - 3
* 4/14/2020
* Paul Ellis
**************** 

OVERVIEW:

 This program is an Indexed Unsorted Double linked list.
 It includes an Iterator and a List Iterator. Uses Nodes
 with links both forward and backwards, and can store any
 Object.


INCLUDED FILES:

 * IUDoubleLinkedList.java - source file
 * ListTester.java - source file
 * IndexedUnsortedList.java - source file
 * Node.java - source file
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac *.java

 Run the compiled class file with the command:
 $ java ListTester

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:
 
 This program is an implementation of a Double Linked Indexed Unsorted List.
 That means that the list is comprised of nodes with a forward and backward
 link to other nodes. It does not use an array so there is no need to shift
 when adding an element to the front or middle of the list. There is also
 no need to loop through the list to add or remove from the tail end because
 of the use of double linkage. Indexed methods are slightly less efficient because
 of the need to loop through n times to find the element. This program is driven
 by the ListTester class which tests change scenarios and every way to modify
 each scenario.
 
 This IUDoubleLinkedList also has ListIterator and Iterator capabilities.
 The classes for this are subclasses in the main IUDoubleLinkedList class.
 The Node class is the structure used to store elements and consists of
 getter and setter methods for the Element, Next Node, and Previous Node.
 IUDoubleLinkedList inherits its methods from the IndexedUnsortedList Interface.
 
 The development of the methods in IUDoubleLinkedList was driven by the test
 class and fixing the errors that were shown. I worked my way from an empty
 list to a three element list and fixed the methods as they were tested.
 Some of the methods were brought straight over from a single linked list
 such as size, get(index), contains, and isEmpty. These were brought over
 because they will not need to take into consideration the backwards link.

TESTING:

 Testing for this program was driven by the ListTester class which tests
 change scenarios for a List. It starts off with an empty list that then
 is populated in each different way (add, addToFront, addToRear, etc.),
 and after each different change to a list it runs through all of the ways
 to confirm that the list is not broken and the method that changed it
 worked the way it was supposed to work. The tests go from an empty list
 to a three element list and back down to an empty list. Because the double
 linked list implements a ListIterator we repeat this with each way to change
 the list with a ListIterator. The errors are printed to the console in an
 easily understandable fashion that allows the reader to know exactly what
 change failed. At the end of the test the program prints out the total
 tests run, passed, failed, and percentage passed. The program works when
 there are no test that fail. There is one test that fails for one change
 scenario. The AB_addAfterCA_ACB_testListIter2Previous. I am not sure why
 this is failing and cannot see where it is going wrong.

DISCUSSION:
 
 During the development of this program I encountered few struggles,
 the biggest one being over thinking in certain methods such as, ListIter.remove().
 This was a weird method because you have to know if the last returned element
 was due to a call from next or previous. Thinking about this gave me a
 headache and when we went over it in class I had an even larger headache.
 
 Dealing with the backwards links was a part of this project that just
 clicked for me from the beginning. I would draw pictures to show
 what links there were and how to change the links such that no nodes
 were lost to garbage collection. It was easy for me to visualize the
 links when I drew the pictures, which I didn't do for the Single 
 linked list and those links were just not making sense to me.
 
 The only problem I had was in the testing of the program. One test was failing
 for no reason and I cannot figure out what is going wrong. I used the
 debug feature to try and figure out what was going on but it just wasn't
 showing me where it went wrong. I figured that since the test didn't fail for
 any other scenarios that were identical that it was not that big of a deal.