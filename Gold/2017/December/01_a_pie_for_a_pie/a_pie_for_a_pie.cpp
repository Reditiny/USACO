#include <iostream>
#include <algorithm>
#include <set>
#include <queue>
using namespace std;
#define MAXN 100000
#define INF 1000000000

int N,D;
int A[2*MAXN];
int B[2*MAXN];
int dist[2*MAXN];

struct cmpA{
    bool operator()(int a,int b) const{
        return B[a]<B[b];
    }
};

struct cmpB{
    bool operator()(int a,int b) const{
        return A[a]<A[b];
    }
};

multiset<int,cmpA> sA;
multiset<int,cmpB> sB;
queue<int> que;

int main(){
    freopen("piepie.in", "r", stdin);
    freopen("piepie.out", "w", stdout);
    cin >> N >> D;
    for(int i=0;i<2*N;i++){
        cin >> A[i] >> B[i];
        A[i] = -A[i], B[i] = -B[i];
        dist[i] = -1;
    }
    // 每个饼都是图中的一个节点
    // 如果Bessie在收到饼a后能把饼b送给Elsie(或者Elsie送给Bessie)，节点a和节点b之间存在一条有向边
    // 分值为 0 的点作为 BFS 的起点
    for(int i=0;i<N;i++){
        if(B[i]==0)
            que.push(i), dist[i] = 1;
        else
            sA.insert(i);
        if(A[N+i]==0)
            que.push(N+i), dist[N+i] = 1;
        else
            sB.insert(N+i);
    }
    // 通过有序的 multiset 保存饼，通过 lower_bound 函数找到满足条件的饼
    // lower_bound(i) 返回第一个大于等于x的数的迭代器
    multiset<int,cmpA>::iterator itA;
    multiset<int,cmpB>::iterator itB;
    while(!que.empty()){
        int i = que.front();
        if(i < N){
            while(1){
                itB = sB.lower_bound(i);
                if(itB == sB.end() || A[*itB] > A[i]+D)
                    break;
                dist[*itB] = dist[i] + 1;
                que.push(*itB);
                sB.erase(itB);
            }
        }else{
            while(1){
                itA = sA.lower_bound(i);
                if(itA == sA.end() || B[*itA] > B[i]+D)
                    break;
                dist[*itA] = dist[i] + 1;
                que.push(*itA);
                sA.erase(itA);
            }
        }
        que.pop();
    }
    for(int i=0;i<N;i++)
        cout << dist[i] << '\n';
}