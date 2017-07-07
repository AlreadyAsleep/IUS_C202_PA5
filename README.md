# IUS_C202_PA5
Programming Assignment 5 for Dr. H's 202 class

### Abstract

The problem here is the same as the one from [Programming Assignment 4](../IUS_C202_PA4/blob/master/README.md).

The implementation varies slightly however, a binary search tree is used to query the random dictionary file to search for words as opposed to a linear data structure in PA4. A binary search tree is a data structure that orders its elements in comparison to each other allowing for much faster traversal and eliminates the need for the copious amount of comparisons from PA4. 

Since the data we used for our tree was randomized to a fair degree, we can work under the assumption that the time complexity for traversal is O(log<sub>2</sub>N), far surpassing the O(N) time for linear teaversal and retrieval. As such, the program accomplished its task in a mere two seconds as oppposed to the 1'41'' from PA4. Clearly, the binary search tree method is superior to linked lists in this problem.

### Outputs

Words found : 796518

Words not found: 173165

Comparisons made to found words: 13085482

Comparisons made to missing words: 2583696

Average comparisons for found words: 16.428356

Average comparisons for missing words: 14.920428

BUILD SUCCESSFUL (total time: 2 seconds)
