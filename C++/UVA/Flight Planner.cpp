#include <bits/stdc++.h>

using namespace std;

// Input macros
#define fast ios_base::sync_with_stdio(0);cin.tie(0);
#define si(n) scanf("%d",&n)
#define sii(n, m) scanf("%d%d",&n,&m)
#define siii(n, m, x) scanf("%d%d%d",&n,&m,&x)
#define sc(n) scanf(" %c",&n)
#define sd(n) scanf("%lf",&n)
#define read(s) freopen(s, "r", stdin)
#define write(s) freopen(s, "w", stdout);
#define endl '\n'

// Useful constants
#define INF (int)1e9
#define EPS 1e-9

// Useful hardware instructions
#define bitcount __builtin_popcount
#define gcd __gcd

// Traversal macros
#define forn(i,n) for (int i = 0; i < n; i++)
#define forr(i,n) for (int i = n-1; i >= 0; i--)
#define forall(i,a,b,x) for (int i = a; i < b; i+=x)
#define foreach(v, c) for (typeof(c.begin()) v = c.begin(); v != c.end(); v++)

// Useful container manipulation
#define all(a) a.begin(), a.end()
#define in(a, b) (b.find(a) != b.end())
#define pb push_back
#define fill(a,v) memset(a, v, sizeof a)
#define sz(a) ((int)(a.size()))
#define mp make_pair
#define f first
#define s second
#define parseInt(x) atoi(x.c_str())
#define parseLong(x) atol(x.c_str())
#define charToInt(x) x - '0'
#define intToChar(x) x + '0'
#define toString(x) dynamic_cast< std::ostringstream & >( \
( std::ostringstream() << std::dec << x ) ).str()

// Printing
#define print(a,s) {forn(i, s) { if (i > 0) cout << " "; cout << a[i];} puts("");}

typedef long long ll;
typedef pair<int, int> ii;
typedef pair<long long, long long> llll;
typedef vector<int> vi;
typedef vector<long long> vl;
typedef vector<pair<int, int> > vii;
typedef vector<pair<long long, long long> > vll;

int t, x, n;
int a[11][1001];
int dp[1001][11];

int rec(int idx, int altitude);
int main()
{
	si(t);
	while(t--)
	{
		si(x);

		n = x/100;
		forn(i, 10)
			forn(j, n)
				si(a[i][j]);

		fill(dp, -1);
		printf("%i\n\n", rec(0, 9));
	}
}

int rec(int idx, int altitude)
{
	if (idx == n)
	{
		if (altitude == 9)
			return 0;
		return INF;
	}

	if (altitude > 9 || altitude < 0)
		return INF;

	if (dp[idx][altitude] != -1)
		return dp[idx][altitude];

	int up = rec(idx+1, altitude-1) + (60 - a[altitude][idx]);
	int stay = rec(idx+1, altitude) + (30 - a[altitude][idx]);
	int down = rec(idx+1, altitude+1) + (20 - a[altitude][idx]);

	return dp[idx][altitude] = min(min(up, stay), down);
}