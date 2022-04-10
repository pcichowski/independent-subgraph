# Independent subgraph in social circles

## Algorithm
The task in question is to find the largest possible independent subgraph in a sparse graph given - this problem is known to be NP-complete.

An independent subgraph is a graph, shuch that none of its nodes are mututally adjacent in the original, larger graph.

I used a greedy algorithm that selects a node with the smallest degree, adds it to the result set and removes the node and its neighbours from the graph.
It produces a suboptimal solution, but it has been shown that its results are satisfactory and 
computational complexity is known to be O(n + m)

## Sample graph

The graph used is generated from social circles from Facebook. The dataset was gathered by SNAP and the data has been anonymized.
It contains 4039 nodes and 88234 edges.

My program found a subgraph with 1015 nodes, which may actually be very close to the optimal solution.

## Examples
![image](https://user-images.githubusercontent.com/81694867/162609782-806af120-d3c7-4665-b970-1cdd9ce679b4.png)

## Acknowledgements
Based on a paper from Algorithmica journal
- Halldórsson, M.M., Radhakrishnan, J. Greed is good: Approximating independent sets in sparse and bounded-degree graphs. Algorithmica 18, 145–163 (1997). https://doi.org/10.1007/BF02523693

Dataset provided by SNAP
- Jure Leskovec and Andrej Krevl, Stanford Large Network Dataset Collection, http://snap.stanford.edu/data/ego-Facebook.html
- J. McAuley and J. Leskovec. Learning to Discover Social Circles in Ego Networks. NIPS, 2012
