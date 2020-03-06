#include<bits/stdc++.h>
using namespace std;

typedef struct process
{
    string p_id;
    int aT;
    int cpuT;
    int pr;
    int cT; // cpmpilation time
    int tat;
    int wt;
} proccess;

bool compare(proccess a, process b)
{
    return a.aT < b.aT;
}

bool compare2(proccess a, process b)
{
    return a.pr < b.pr;
}


int main()
{
    proccess pro[100];
    int n;
    double toTat = 0, toWt = 0;
    cout<<"Enter number of process: ";
    cin>>n;

    cout<<"Enter the process->arrT->cpuT->pr\n";
    for(int i = 1; i <= n; i++)
    {
        cin>>pro[i].p_id;
        cin>>pro[i].aT;
        cin>>pro[i].cpuT;
        cin>>pro[i].pr;
    }

    sort(pro+1,pro+1+n,compare);
    queue <string> ganttChart;


    pro[1].cT = pro[1].cpuT + pro[1].aT;
    pro[1].tat = pro[1].cT - pro[1].aT;
    pro[1].wt = pro[1].tat - pro[1].cpuT;

    ganttChart.push(pro[1].p_id);

    int i = 2,j;

    while(i < n)
    {
        for( j = i; j <= n; j++)
        {
            if(pro[j].aT > pro[i - 1].cT)
                break;
        }

        sort(pro+i,pro+i+(j-i),compare2);
        pro[i].cT = pro[i-1].cT + pro[i].cpuT;
        pro[i].tat = pro[i].cT - pro[i].aT;
        pro[i].wt = pro[i].tat - pro[i].cpuT;
        ganttChart.push(pro[i].p_id);
        i++;
    }
    pro[i].cT = pro[i-1].cT + pro[i].cpuT;
    pro[i].tat = pro[i].cT - pro[i].aT;
    pro[i].wt = pro[i].tat - pro[i].cpuT;

    ganttChart.push(pro[i].p_id);

    cout<<"Process\tArrT\tCpuT\tPri\tWT\tTaT\n\n";

    for(int i = 1; i <= n; i++)
    {
        cout<<pro[i].p_id<<"\t"<<pro[i].aT<<"\t"<<pro[i].cpuT<<"\t"<<pro[i].pr<<"\t"<<pro[i].wt<<"\t"<<pro[i].tat<<"\n";
        toTat += pro[i].tat;
        toWt += pro[i].wt;
    }

    cout<<"\n\nAverage waiting time = "<<toWt/n<<"\n";
    cout<<"Average tarn around time = "<<toTat/n<<"\n";

    cout<<"\n\nGantt Chart:\n";
    while(!ganttChart.empty())
    {
        cout<<ganttChart.front();
        ganttChart.pop();
        if(!ganttChart.empty()) cout<<"-->";
    }

    cout<<"\n\n\n";
}

