#include <bits/stdc++.h>

using namespace std;

int main()
{
	int n;

	scanf("%i",&n);

	int occ[1000001];
	memset(occ, 0, sizeof occ);

	int index = -1;
	int maximum = 0;
	for (int i = 0; i < n; i++)
	{
		int a;
		scanf("%i",&a);

		occ[a]++;
		if (occ[a] > maximum)
		{
			maximum = occ[a];
			index = a;
		}
	}

	printf("%i\n", index);
}
