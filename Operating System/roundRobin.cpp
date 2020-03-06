#include<bits/stdc++.h>

using namespace std;

int wt[100];
int tat[100];


typedef struct roundRobin
{
    int p_id;
    int aT;
    int cpuT;
    int remT;
} process;

process pro[100];

queue <process> Que;
queue <process> ganttChart;
int quantam = 0, n, totalT;
double totalTat, totalWt;

bool compare(process a, process b)
{
    return a.aT<b.aT ;
}


void insertProcessIfArive()
{
    for(int i = 1; i <= n; i++)
    {
        if(pro[i].aT <= totalT && pro[i].aT > (totalT - quantam))
            Que.push(pro[i]);
    }

}

int main()
{
    cout<<"Enter Process number:";
    cin>>n;
    //process pro[n+1];

    cout<<"Enter process-> arrival time -> cpu time\n";

    for(int i = 1; i <= n; i++)
    {
        cin>>pro[i].p_id>>pro[i].aT>>pro[i].cpuT;
        pro[i].remT = pro[i].cpuT;
    }

    cout<<"Enter quantum : ";
    cin>>quantam;

    sort(pro+1, pro + n+ 1, compare);




    Que.push(pro[1]);
    totalT = 0;

    while(!Que.empty())
    {
        process temp;
        temp = Que.front();

         wt[temp.p_id]+= totalT - temp.aT;

        if(temp.remT > quantam)
        {
            temp.remT -= quantam;
            totalT += quantam;
            temp.aT = totalT; // to calculate waiting time

            insertProcessIfArive();
            Que.push(temp);
            ganttChart.push(temp);
            Que.pop();
        }

        else if(temp.remT <= quantam)
        {
            totalT += temp.remT;
            temp.remT = 0;

            ganttChart.push(temp);
            Que.pop();

            insertProcessIfArive();

        }

    }


    for(int i = 1; i <= n; i++)
    {
        tat[pro[i].p_id] = pro[i].cpuT + wt[i];
        totalTat += tat[pro[i].p_id];
        totalWt += wt[pro[i].p_id];
    }


    cout<<"\n\nProcess\tWt\tTat\n";

    for(int i = 1; i <= n; i++)
    {
        cout<<"P"<<pro[i].p_id<<"\t"<<wt[pro[i].p_id]<<"\t"<<tat[pro[i].p_id]<<"\n";
    }

    cout<<"\nAgerage waiting time = "<<totalWt/n<<"\n";
    cout<<"\nAgerage Tarn around time = "<<totalTat/n<<"\n";


    cout<<"\n\n\nGantt chart\n";

    while(!ganttChart.empty())
    {
        process p = ganttChart.front();
        ganttChart.pop();

        cout<<"P"<<p.p_id;
        if(!ganttChart.empty()) cout<<"-->";
    }

    cout<<"\n\n";

}


/*

1 4 5
2 0 7
3 6 9
4 10 9

*/
