#include<bits/stdc++.h>

using namespace std;
int n,e;
bool flag = false;
int cycle[100];
vector<int>V[100];
int color[100];

int mainNode,k;

void printTheCycle()
{
    for(int i = 0; i < k; i++)
        cout<<cycle[i]<<" ";
    cout<<"\n";
}

void DFS(int u)
{
    color[u] = 1;
    cycle[k++] = u;
    for(int i = 0; i < V[u].size(); i++)
    {
        int v = V[u][i];
        if(v == mainNode)
        {
            cout<<"A cycle detected\n";
            printTheCycle();
            return;
        }
        else if(color[v] == 0)
        {
            DFS(v);
        }
    }
}


int main()
{

    cout<<"Enter node & edge number : ";
    cin>>n>>e;

    cout<<"Enter edges:\n";
    for(int i = 1; i<= e; i++)
    {
        int u,v;
        cin>>u>>v;
        V[u].push_back(v);
    }

    for(int i = 1; i <= n; i++)
    {

        if(color[i] == 0)
        {
            k = 0;
            mainNode = i;
            DFS(i);
        }

    }





    return 0;



}


