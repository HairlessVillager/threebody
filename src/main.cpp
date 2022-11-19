#include <graphics.h>
#include <math.h>
#include <stdio.h>

#define N   3

double G = 200;
double dt = 0.02;
double T = 0.1;
double t = 0;

typedef struct {
	double x;
	double y;
	double z;
	double vx;
	double vy;
	double vz;
	double m;
} body;

body bodys[N] = {
	194.000872,
	0.0,
	-48.617506,
	18.6481474,
	0.0,
	17.2946292,
	16.0,

	-194.000872,
	0.0,
	48.617506,
	18.6481474,
	0.0,
	17.2946292,
	16.0,

	0.0,
	0.0,
	0.0,
	-37.2962948,
	0.0,
	-34.5892584,
	16.0,
};

double S(int i, int j)
{
	double dx, dy, dz;
	dx = bodys[i].x - bodys[j].x;
	dy = bodys[i].y - bodys[j].y;
	dz = bodys[i].z - bodys[j].z;
	return pow(pow(dx, 2.0) + pow(dy, 2.0) + pow(dz, 2.0), 0.5);
}

double F(int i, int j)
{
	return G * bodys[i].m * bodys[j].m / pow(S(i, j), 2.0);
}

int main()
{
	initgraph(640, 480, INIT_RENDERMANUAL);
	setbkcolor(0x000000);
	setfillcolor(0xcfcfcf);
	setcolor(0xcfcfcf);
	ege_enable_aa(1);

	for(; is_run(); delay_fps(60)){
		if(!(t < T)) {
			break;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i != j) {
					bodys[i].vx += dt * G * F(i, j) *
					               ((bodys[j].x - bodys[i].x) / S(i, j))
					               / bodys[i].m;
					bodys[i].vy += dt * G * F(i, j) *
					               ((bodys[j].y - bodys[i].y) / S(i, j))
					               / bodys[i].m;
					bodys[i].vz += dt * G * F(i, j) *
					               ((bodys[j].z - bodys[i].z) / S(i, j))
					               / bodys[i].m;
				}
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i != j) {
					bodys[i].x += dt * bodys[i].vx;
					bodys[i].y += dt * bodys[i].vy;
					bodys[i].z += dt * bodys[i].vz;
				}
			}
		}

		cleardevice();
		xyprintf(0, 0, "%f%%", t / T);
		for(int i = 0; i < N; i++){
		    fillellipse(bodys[i].x + 320, bodys[i].z + 240, 10, 10);
		}

		for(int i = 0; i < N; i++) {
			printf("%lf %lf\n", bodys[i].x, bodys[i].z);
		}
		printf("\n");
		t += dt;
	}

	closegraph();
	return 0;
}

/*
// �ǿ���������������Ϊscr��׺�������ֹ��ĺ�׺����Ҫֱ������exe
#include "graphics.h"
#include <time.h>
#include <stdio.h>

#define MAXSTAR 2000 // ��������
int sc_width, sc_heigh; // ��¼���ڿ��
int g_max;

struct STAR {
    double x;
    int y;
    double step;
    int color;
} star[MAXSTAR];

// ��ʼ������
void InitStar( int i ) {
    double speed = 0.006;
    star[i].x = 0;
    star[i].y = random( sc_heigh );
    star[i].step = randomf() * speed * 0.9 + speed * 0.1;
    star[i].color = ( int )( star[i].step * 255 / speed + 0.5 ); // �ٶ�Խ�죬��ɫԽ��
    if ( star[i].color > 255 ) {
        star[i].color = 255;
    }
    star[i].color = RGB( star[i].color, star[i].color, star[i].color );
}

// �ƶ�����
void MoveStar( int i, double dt ) {
    // ����ԭ��������
    putpixel( ( int )( star[i].x * sc_width ), star[i].y, 0 );
    // ������λ��
    star[i].x += star[i].step * dt * 60;
    if ( star[i].x > 1 ) InitStar( i );
    // ��������
    putpixel( ( int )( star[i].x * sc_width ), star[i].y, star[i].color );
}

int preinit( int argc, char* argv[] ) {
    setinitmode( INIT_NOBORDER | INIT_TOPMOST ); // ָ����ʼ��Ϊ�ޱ߿򶥲㴰�ڣ����Ҵ������Ͻ�����Ϊ(0, 0)
    g_max = MAXSTAR;
    if ( argc < 2 ) {
        //MessageBoxA( NULL, "����Ļ��������������", "�ǿ�����", MB_OK );
        //return -1;
    } else if ( stricmp( argv[1], "/p" ) == 0 ) { // С����Ԥ��ģʽ
        HWND hwnd;
        sscanf( argv[2], "%d", &hwnd );
        attachHWND( hwnd ); // ��ege����
        setinitmode( INIT_NOBORDER | INIT_CHILD | INIT_WITHLOGO ); // ָ����ʼ��Ϊ�ޱ߿��Ӵ���
        g_max = 200;
        return 1;
    } else if ( stricmp( argv[1], "/s" ) ) { // �ǲ�������ģʽ
        MessageBoxA( NULL, "����Ļ��������������", "�ǿ�����", MB_OK );
        return -1;
    }
    return 0; // ȫ��ģʽ
}

// ������
int main( int argc, char* argv[] ) {
    int i, ms_x = -1024, ms_y = -1024, exitflag = 0;
    int fps = 60;
    double dtime;

    int mode = preinit( argc, argv ); // ��¼��ʼ��ģʽ
    if ( mode < 0 ) return 0;

    randomize(); // ��ʼ���������
    initgraph( -1, -1 ); // ��ͼ�δ��ڣ���ȫ��ģʽ

    showmouse( mode );
    sc_width = getwidth();
    sc_heigh = getheight();

    // ��ʼ����������
    for ( i = 0; i < g_max; i++ ) {
        InitStar( i );
        star[i].x = randomf();
    }
    // �����ǿգ�����������ƶ�����˳�
    setfont( 12, 6, "����" );
    setrendermode( RENDER_MANUAL );
    dtime = fclock();
    while ( kbmsg() ) getkey();

    for ( ; !exitflag && is_run() && kbmsg() == 0; delay_fps( fps ) ) { //ÿ�뻭120֡��kbhit(1)�ǻ�ȡ�������������Ϣ�����pdf
        // ����������Ϣ
        while ( mousemsg() ) {
            mouse_msg msg = getmouse();
            if ( ms_x <= -1024 ) {
                ms_x = msg.x;
                ms_y = msg.y;
            }
            // ������꣬�ƶ�������Χ���˳�
            if ( mode == 0 ) { // ��ȫ��ģʽ�Ŵ������
                int x = msg.x, y = msg.y;
                x -= ms_x;
                y -= ms_y;
                if ( x * x + y * y > 400 ) exitflag = 1;
            }
        }
        // ��ʾ����
        double dt = 1.0 / fps; //fclock() - dtime;
        dtime += dt;
        for ( int i = 0; i < g_max; i++ ) {
            MoveStar( i, dt );
        }
        // ��ʾFPS
        {
            char str[60];
            sprintf( str, "%8.2f FPS", getfps());
            outtextxy( 0, 0, str ); //��ʾfps
        }
    }
    closegraph(); // �ر�ͼ�δ���
    return 0;
}

*/
