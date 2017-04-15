// C++ Implementation of Kosaraju's algorithm to print all SCCs
#include <iostream>
#include <sstream>
#include <fstream>
#include <list>
#include <stack>
using namespace std;
int count = 0;
int top5_leaders_size[6];
class Graph
{
    int V;    // No. of vertices
    list<int> *adj;    // An array of adjacency lists

    // Fills Stack with vertices (in increasing order of finishing
    // times). The top element of stack has the maximum finishing 
    // time
    void fillOrder(int v, bool visited[], stack<int> &Stack);

    // A recursive function to print DFS starting from v
    void DFSUtil(int v, bool visited[]);
public:
    Graph(int V);
    void addEdge(int v, int w);

    // The main function that finds and prints strongly connected
    // components
    void printSCCs();

    void show_top5_leaders_size (int c);
    void insert_top5 (int pos, int leader_size);
    // Function that returns reverse (or transpose) of this graph
    Graph getTranspose();
};

Graph::Graph(int V)
{
    this->V = V;
    adj = new list<int>[V];
}

// A recursive function to print DFS starting from v
void Graph::DFSUtil(int v, bool visited[])
{
    // Mark the current node as visited and print it
    visited[v] = true;
    cout << v << " ";
    count++;
    // Recur for all the vertices adjacent to this vertex
    list<int>::iterator i;
    for (i = adj[v].begin(); i != adj[v].end(); ++i)
        if (!visited[*i])
            DFSUtil(*i, visited);
}

Graph Graph::getTranspose()
{
    Graph g(V);
    for (int v = 0; v < V; v++)
    {
        // Recur for all the vertices adjacent to this vertex
        list<int>::iterator i;
        for(i = adj[v].begin(); i != adj[v].end(); ++i)
        {
            g.adj[*i].push_back(v);
        }
    }
    return g;
}

void Graph::addEdge(int v, int w)
{
    adj[v].push_back(w); // Add w to v’s list.
}

void Graph::fillOrder(int v, bool visited[], stack<int> &Stack)
{
    // Mark the current node as visited and print it
    visited[v] = true;

    // Recur for all the vertices adjacent to this vertex
    list<int>::iterator i;
    for(i = adj[v].begin(); i != adj[v].end(); ++i)
        if(!visited[*i])
            fillOrder(*i, visited, Stack);

    // All vertices reachable from v are processed by now, push v 
    Stack.push(v);
}

// The main function that finds and prints all strongly connected 
// components
void Graph::printSCCs()
{
    stack<int> Stack;

    // Mark all the vertices as not visited (For first DFS)
    bool *visited = new bool[V];
    for(int i = 0; i < V; i++)
        visited[i] = false;

    // Fill vertices in stack according to their finishing times
    for(int i = 0; i < V; i++)
        if(visited[i] == false)
            fillOrder(i, visited, Stack);

    // Create a reversed graph
    Graph gr = getTranspose();

    // Mark all the vertices as not visited (For second DFS)
    for(int i = 0; i < V; i++)
        visited[i] = false;

    // Now process all vertices in order defined by Stack
    while (Stack.empty() == false)
    {
        // Pop a vertex from stack
        int v = Stack.top();
        Stack.pop();

        // Print Strongly connected component of the popped vertex
        if (visited[v] == false)
        {
	    count = 0;
            gr.DFSUtil(v, visited);
	    cout << endl;
	    cout << "count : " << count << endl;
            show_top5_leaders_size (count);
        }
    }
}
void Graph::insert_top5 (int pos, int leader_size)
{
    int i = 4;
    for (; i > pos; i--) {
        top5_leaders_size[i] = top5_leaders_size[i - 1];
    }
    top5_leaders_size[pos] = leader_size;
}

void Graph::show_top5_leaders_size (int count)
{
    int i = 0;
        int leader_size = count;
        if (leader_size > 0) {
            if (leader_size > top5_leaders_size[0]) {
                insert_top5 (0, leader_size);
            } else if (leader_size > top5_leaders_size[1]) {
                insert_top5 (1, leader_size);
            } else if (leader_size > top5_leaders_size[2]) {
                insert_top5 (2, leader_size);
            } else if (leader_size > top5_leaders_size[3]) {
                insert_top5 (3, leader_size);
            } else if (leader_size > top5_leaders_size[4]) {
                insert_top5 (4, leader_size);
            }
        }
}

// Driver program to test above functions
int main()
{
    // Create a graph given in the above diagram
    int i, u, v, countCC, q;
    int ver[2];
    int buf;
    Graph g(10000000);
    ifstream file("scc.txt");
    string str;
    while (getline(file, str)) {
	stringstream ss(str);
	i = 0;
	while( ss >> buf) {
	   ver[i] = buf;
	   i++;
	}
//	cout << ver[0] <<"   "<< ver[1] << endl;
	g.addEdge(ver[0],ver[1]);
    }
  /*	
    Graph g(5);
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 1);
    g.addEdge(0, 3);
    g.addEdge(3, 4);
*/
    cout << "Following are strongly connected components in "
            "given graph \n";
    g.printSCCs();
    for ( i = 0 ; i < 5; i++) {
	cout << "final counts" << top5_leaders_size[i] << endl;
    }
    return 0;
}
