# ZerglingSpeed
A test platform for boid algorithms for cooperative pathfinding of large groups of colliding agents.

Say you have a bunch of agents pathing to the same point... How can you make sure they can move there together, rather than in single file?
![image](https://cloud.githubusercontent.com/assets/8551479/10560929/6a4dcf8a-74e7-11e5-8f18-09b7b662432e.png)

## How it works
Using [boids](https://en.wikipedia.org/wiki/Boids), we can ensure that our agents avoid each other and obstacles, while still moving towards the final goal. The boids used here have the following desires, represented as vectors:

* **Seperation** - agents prefer to stay a minimum distance from nearby agents
* **Goal-seeking** - agents are always moving toward their individual goals

We can simply find a movement vector that is the weighted vector sum of these two desires. By changing the weights of the desires, one can affect the behaviour of the agent as a whole.

## Results
The result of these two simple desires is emergent behaviour: boids slide smoothly past each other on the way to the goal.

This algorithm can be extended to an arbitrary number of agents, all with differing goals.
![image](https://cloud.githubusercontent.com/assets/8551479/10560925/5a0a7286-74e7-11e5-97f9-8f5bdefc5a98.png)

This makes it ideal for implementing RTS pathfinders, where many units may be moving in many directions at any given time.

## Further Comments
One difficulty that this algorithm has is that it does not deal with obstacles well. Regular pathfinders have (fairly) large graph searches to find a correct path - this one simply points in the direction of the goal itself and can easily be stuck in a local minima. It may be necessary to implement a static pathfinder underneath the boid implementation to identify the (static, agent-free) route to the goal, then adjust the goal-vector to point along the path as the agent moves towards its goal.
