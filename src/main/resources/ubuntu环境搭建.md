ubuntu安装中文版



### ubuntu终端桌面中文修改为英文

#### 方法一：

STEP1: 将这些目录修改为英文名

```
mv 桌面 Desktop 
```

STEP2: 修改配置文件  ～/.config/user-dirs.dirs ，将对应的路径改为英文名（要和STEP1中修改的英文名对应）

```
vim ~/.config/user-dirs.dirs
```

如果安装的是中文版[Ubuntu](http://www.linuxidc.com/topicnews.aspx?tid=2)，那么/home下的目录会是“桌面”“下载”等，在终端下进入这些目录看起来很不爽，那怎样改为英文目录呢，很简单：

STEP1: 将这些目录修改为英文名，如：  mv 桌面 Desktop 

STEP2: 修改配置文件  ～/.config/user-dirs.dirs ，将对应的路径改为英文名（要和STEP1中修改的英文名对应）

```
vim ~/.config/user-dirs.dirs
```

配置文件修改后的内容如下：

```
XDG_DESKTOP_DIR="$HOME/Desktop"
XDG_DOWNLOAD_DIR="$HOME/Download"
XDG_TEMPLATES_DIR="$HOME/Template"
XDG_PUBLICSHARE_DIR="$HOME/Public"
XDG_DOCUMENTS_DIR="$HOME/Document"
XDG_MUSIC_DIR="$HOME/Music"
XDG_PICTURES_DIR="$HOME/Picture"
XDG_VIDEOS_DIR="$HOME/Video"
```

#### 方法二：

 一、打开终端，在终端中输入命令:

```
export LANG=en_US
xdg-user-dirs-gtk-update
```

 跳出对话框询问是否将目录转化为英文路径,同意并关闭. 

二、在终端中输入命令:

```
export LANG=zh_CN
```

关闭终端,并重起.下次进入系统,系统会提示是否把转化好的目录改回中文.选择不再提示,并取消修改.主目录的中文转英文就完成了~ 

### 改变ubuntu终端显示语言

（桌面系统是中文，终端提示是英文）

1.打开终端：

```
vi .bashrc
```

2.最后添加：

```shell
if [ "$TERM"="linux" ] ;then
  export LANGUAGE=en_US
  export LANG=en_US.UTF-8
fi
```

关闭当前终端，重新打开终端后命令中的提示就显示英文提示了。(注：此方法在bash和zsh上均有效 )

注意对那些中文文件名，文件夹名会显示为乱码。
实际是改变系统两个环境变量 `LANGUAGE` 和 `LANG`的值（可以用echo `$LANG 来查看值）

### Ubuntu18.04下安装搜狗输入法

首先，安装Fcitx输入框架

```
sudo apt install fcitx
```

其次，上搜狗输入法官网下载Linux版本搜狗输入法（32位和64位根据自己情况，在虚拟机上用浏览器下载即可)

下载页面  https://pinyin.sogou.com/linux/?r=pinyin

然后进入相应的下载目录，进行安装（安装过程中如果有错，运行**sudo apt  --fix-broken install** 或者 **sudo apt install -f **） 

```
http://cdn2.ime.sogou.com/dl/index/1524572264/sogoupinyin_2.2.0.0108_amd64.deb?st=PAeYwICqog6RXW0wHgVVFw&e=1527130824&fn=sogoupinyin_2.2.0.0108_amd64.deb
sudo dpkg -i sogoupinyin_2.2.0.0108_amd64.deb
```



###  ubuntu18.04安装chromium浏览器

```
sudo add-apt-repository ppa:a-v-shkop/chromium
sudo apt-get upate
sudo apt-get install chromium-browser
```

### 安装优化工具

```
gnome桌面  终端：sudo apt install gnome-tweak-tool
```

```
unity桌面，终端：sudo apt install unity-tweak-tool
```

### 安装vscode

```
vscode下载： https://code.visualstudio.com/
```

```
打开终端(快捷键：Ctrl+Alt+T)
运行如下命令:
`sudo dpkg -i vscode.deb`
如果安装失败，则需要运行如下命令,再运行上面命令安装:
`sudo apt-get install -f`
之后再次安装即可以成功，相关资料可参考官方文档:
`https://code.visualstudio.com/docs/setup/linux`
```

### 终端美化

终端采用zsh和oh-my-zsh，既美观又简单易用，主要是能提高你的逼格！！！ 

首先，安装zsh： 

```
sudo apt-get install zsh
```

接下来我们需要下载 oh-my-zsh 项目来帮我们配置 zsh，采用wget安装 

```
wget https://github.com/robbyrussell/oh-my-zsh/raw/master/tools/install.sh -O - | sh
```

取代bash，设为默认shell

```
sudo usermod -s /bin/zsh username
```

或者

```
chsh -s /bin/zsh1
```

```
chsh -s `which zsh`1
```

如果要切换回去bash：

```
chsh -s /bin/bash
```



### vimrc配置

```
https://github.com/amix/vimrc
```



### ubuntu 垃圾清理，卸载不必要应用

安装完系统之后，需要更新一些补丁。Ctrl+Alt+T调出终端，执行一下代码： 

```shell
sudo apt-get update 
sudo apt-get upgrade
```

### 卸载libreOffice

```shell
sudo apt-get remove libreoffice-common  
```

### 删除Amazon的链接

```shell
sudo apt-get remove unity-webapps-common 
```

### 删除不常用的软件

```shell
sudo apt-get remove thunderbird totem rhythmbox empathy brasero simple-scan gnome-mahjongg aisleriot 
sudo apt-get remove gnome-mines cheese transmission-common gnome-orca webbrowser-app gnome-sudoku  landscape-client-ui-install  
sudo apt-get remove onboard deja-dup 
```

**一条命令清除旧内核** 

```shell
dpkg -l 'linux-*' | sed '/^ii/!d;/'"$(uname -r | sed "s/\(.*\)-\([^0-9]\+\)/\1/")"'/d;s/^[^ ]* [^ ]* \([^ ]*\).*/\1/;/[0-9]/!d' | xargs sudo apt-get -y purge
```

### 安装驱动

nvidia官方文档

```
http://wiki.ubuntu.org.cn/NVIDIA
```



####手动去官网下载.run文件自己安装**

**NVIDIA驱动** 

```
http://www.nvidia.com/Download/index.aspx?lang=en-us
```

```
http://us.download.nvidia.com/XFree86/Linux-x86_64/390.59/NVIDIA-Linux-x86_64-390.59.run
```

**卸载原先的所有驱动：** 

```
#for case1: original driver installed by apt-get:  
sudo apt-get remove --purge nvidia*
  
#for case2: original driver installed by runfile:  
sudo chmod +x *.run  
sudo ./NVIDIA-Linux-x86_64-390.59.run --uninstall
```

#### 屏蔽开源驱动nouveau

```
sudo gedit /etc/modprobe.d/blacklist.conf
```

 在最后一行添加： 

```
blacklist nouveau 
blacklist vga16fb
blacklist rivafb
blacklist nvidiafb
blacklist rivatv
```

之后，执行命令更新一下 ：

```
sudo update-initramfs -u  
```

电脑重启之后执行  

```
lsmod | grep nouveau      #没有输出，即说明安装成功
```

#### 安装gcc

```
sudo apt-get install build-essential
```



#### 进入命令行界面

一、重启系统至init 3（文本模式），也可先进入图形桌面再运行init 3进入文本模式，再安装下载的驱动就无问题，

首先我们需要结束x-window的服务，否则驱动将无法正常安装

关闭X-Window，很简单：**sudo service lightdm stop**，然后切换tty1控制台：**Ctrl+Alt+F1**即可

或者

二、**Ctrl-Alt+F3**

给驱动run文件赋予执行权限（若出现[sudo] 计算机名 ◆ ◆ ◆ ◆，这是因为安装了中文的ubuntu，输入登录密码即可）

```
cd Downloads  
sudo chmod a+x NVIDIA-Linux-x86_64-375.20.run  
```

#### 安装(注意 参数)

```
sudo ./NVIDIA-Linux-x86_64-375.20.run –no-opengl-files 
```

```
–no-opengl-files      只安装驱动文件，不安装OpenGL文件。这个参数最重要
–no-x-check          安装驱动时不检查X服务
–no-nouveau-check    安装驱动时不检查nouveau 
后面两个参数可不加。
```

#### 测试

**重启之后在终端内输入：**

```
nvidia-smi        #若出现电脑GPU列表，即安装成功  
或者  
nvidia-settings   #显示你的显卡信息  
```



### 屏幕亮度调节

#### 配置grub使得fn可以调节亮度

首先，修改grub的用户配置文件, /etc/default/grub 

```
sudo vim /etc/default/grub 

将  GRUB_CMDLINE_LINUX=""   改为   
GRUB_CMDLINE_LINUX="acpi_backlight=vendor" 
```

或者使用下面更复杂的配置

```
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash" 
GRUB_CMDLINE_LINUX=""

修改为

acpi_osi=Linux acpi_backlight=vendor
GRUB_CMDLINE_LINUX_DEFAULT=”quiet splash acpi_osi=Linux”
GRUB_CMDLINE_LINUX="acpi_backlight=vendor“
```

更新grub.cfg

```
sudo update-grub 
或者
grub-mkconfig -o /boot/grub/grub.cfg
```

### 终端分屏(Terminator终端终结者)

安装

```shell
sudo apt-get install terminator
```

#### 修改默认终端工具

```
安装dconfig-editor
sudo apt-get install dconf-tools
```

安装成功后，就可以在终端中通过命令  **dconf-editor** 来打开这个工具，然后从左边的的菜单栏中按照下面的步骤依次进入指定的菜单项：
 org > gnome > desktop > applications > terminal
 此时，我们可以看到使用 Terminator 作为默认终端工具的配置为：

```
exec terminator
exec-arg -e
```

如果想使用 gnome-terminal 为默认终端工具的话，就将上面的配置更改为： 

```
exec gnome-terminal
exec-arg -x
```

保存退出之后，使用快捷键 “Ctrl + Alt + T” 启动一个终端时，这时启动起来的就是 gnome-terminal 工具了！ 

#### 配置

```
vim ~/.config/terminator/config
```

```
[global_config]
  borderless = True
  focus = system
  inactive_color_offset = 0.75
  suppress_multiple_term_dialog = True
  title_transmit_bg_color = "#d30102"
[keybindings]
[layouts]
  [[default]]
    [[[child1]]]
      parent = window0
      profile = default
      type = Terminal
    [[[window0]]]
      parent = ""
      type = Window
[plugins]
[profiles]
  [[default]]
    background_color = "#2d2d2d"
    background_darkness = 0.86
    background_type = transparent
    copy_on_selection = True
    cursor_color = "#2D2D2D"
    cursor_shape = ibeam
    font = Ubuntu Mono 13
    foreground_color = "#eee9e9"
    palette = "#2d2d2d:#f2777a:#99cc99:#ffcc66:#6699cc:#cc99cc:#66cccc:#d3d0c8:#747369:#f2777a:#99cc99:#ffcc66:#6699cc:#cc99cc:#66cccc:#f2f0ec"
    show_titlebar = False
    use_system_font = False

```

#### 快捷键

```
 Ctrl+Shift+E 垂直分割窗口
 Ctrl+Shift+O 水平分割窗口
 F11 全屏
 Ctrl+Shift+C 复制
 Ctrl+Shift+V 粘贴
 Ctrl+Shift+N 或者 Ctrl+Tab 在分割的各窗口之间切换
 Ctrl+Shift+X 将分割的某一个窗口放大至全屏使用
 Ctrl+Shift+Z 从放大至全屏的某一窗口回到多窗格界面  
```



###  系统监视器 Conky

```
sudo apt install conky-all
```

#### 安装Conky Manager配置Conky

```
sudo apt-add-repository -y ppa:teejee2008/ppa
sudo apt update
sudo apt install conky-manager
```

#### 下载 Conky 主题包

[Official theme pack download](http://www.teejeetech.in/2014/06/conky-manager-v2-released.html)

[Deluxe Conky theme pack download](http://www.teejeetech.in/2013/07/deluxe-conky-theme-pack.html)

下载完成后，单击Conky Manager工具栏中的导入图标以导入这些主题包。 您可以选择要使用的主题和哪些窗口小部件在桌面上显示。 以下是我桌面上的Conky设置。 我启用了以下小部件。 

有些主题不能直接导入Conky Manager。 您必须解压缩主题，然后将其移动到~/.conky/目录。 之后，您可以在Conky Manager中启用它。 

#### 删除窗口边框

有时一个小部件有自己的窗口边框，这可能不是你想要的。 例如，我启用了具有窗口边框的小工具Conky Seamod。 要删除窗口边框，首先单击编辑窗口小部件按钮并将透明度类型设置为透明。 单击应用按钮。 然后编辑其配置文件。

```
nano ~/.conky/Conky\ Seamod/conky_seamod
```

找到以下变量。

```
own_window_type
```

并将其值更改为`desktop`。

```
own_window_type desktop
```

#### conky开机启动

vim  ~/.config/autostart/conky.desktop

```
 [Desktop Entry]
 Type=Application
 Name=Conky
 Comment=Start conky script
 Exec=conky -d
 OnlyShowIn=GNOME
 X-GNOME-Autostart-Phase=Application
 Name[en_US]=conky.desktop
```



在/etc/profil最下面添加：**/usr/bin/conky & **



### 安装shadowsocks-qt5

项目地址

```
https://github.com/shadowsocks/shadowsocks-qt5
```

下载地址

```
https://github.com/shadowsocks/shadowsocks-qt5/releases
```

安装

```
https://github.com/shadowsocks/shadowsocks-qt5/wiki/%E5%AE%89%E8%A3%85%E6%8C%87%E5%8D%97
如果您在使用Debian Wheezy或者任何比其更新的64位Linux发行版，下载Releases页面的AppImage文件，终端下chmod a+x或者用文件管理器给予可执行权限，以后即可随时双击运行。
```

创建快捷图标

```
/usr/share/applications
vim shadowsocks.desktop
```

```
[Desktop Entry]
Version=1.0
Name=Shadowsocks
GenericName=Shadowsocks
Exec="/usr/local/shadowsocks/shadowsocks"
Path=/usr/local/shadowsocks/
Categories=Application
Terminal=false
Type=Application
Icon=/usr/local/shadowsocks/shadowsocks-qt5.png
StartupWMClass=Shadowsocks;
```

#### apt 使用代理

安装 polipo

```
sudo apt-get install polipo
```

配置polipo

```
sudo vim /etc/polipo/config
```

```
logSyslog = true
logFile = /var/log/polipo/polipo.log
socksParentProxy = "localhost:1080"
socksProxyType = socks5
logLevel=4
```

配置apt 代理

```
sudo vim /etc/apt/apt.conf
```

```
Acquire::http::proxy "http://localhost:8123";
```

#### 终端使用代理

方法一、

配置

```
vim ~/.bashrc
```

```
alias fq="http_proxy=http://localhost:8123"
```

使用

```
fq curl ip.gs 
fq curl www.google.com
```

方法二、

```
vim ~/.bashrc
```

```
export http_proxy=http://localhost:8123
```



### 安装Git及GUI客户端

1、**通过APT源安装Git命令行工具**

这里不建议通过源码进行安装，增加复杂程度，且最新版本的Git在各个方面都会修复，不至于出现不能用的状态。

```
sudo add-apt-repository ppa:git-core/ppa
sudo apt-get update
sudo apt-get install git
```

2、**安装GUI工具GitKraken**

这个应该是Linux平台下最好的GUI工具，能结合GitHub的账号体系一键登录。

下载页面：

<https://www.gitkraken.com/download/linux-deb>

安装：

```
wget https://release.gitkraken.com/linux/gitkraken-amd64.deb
sudo dpkg -i gitkraken-amd64.deb 
```


### Ubuntu合上笔记本，系统不睡眠

Ubuntu 有两种方法。第一种是打开  **System Settings –> Power**（中文版是打开 **系统设置 -> 电源**），然后进行设置。一些用户设置后不会生效。

另一个方法是直接编辑 Login Manager 的配置文件（**logind.conf**）。这个方法基本能生效，建议使用这个。

```
sudo vim /etc/systemd/logind.conf
```

打开文件后修改下面这行：

```
#HandleLidSwitch=suspend
```

改成这样：

```
HandleLidSwitch=ignore
```

配置文件的 “ignore” 值告诉 Ubuntu 当笔记本合上后不要睡眠或挂起。不要改动其它设置然后保存文件。 



### 安装酷我音乐

酷我音乐盒是一个音乐资源非常丰富的音乐播放器。它的源码在：

```
https://github.com/LiuLang/kwplayer
```

尽管它目前不被维护，但还是一款不错的应用。它的snap项目在：

```
https://github.com/liu-xiao-guo/kwplayer
```

你可以在你的16.04及以上的系统上安装这个应用：

```
sudo snap install kwplayer --beta --devmode
```

### 安装网易云音乐

下载地址

```
http://music.163.com/#/download
```

```
wget http://d1.music.126.net/dmusic/netease-cloud-music_1.1.0_amd64_ubuntu.deb

sudo dpkg -i netease-cloud-music_1.1.0_amd64_ubuntu.deb
```

如果安装失败，则使用下面的命令解决依赖：

```
sudo apt-get install -f
```

### 安装TeamViewer

```
https://www.teamviewer.com/en/download/linux/
```

TeamViewer的官网打开很慢，如果你下载失败了，可以使用下面的命令下载，下载的包保存在~/Downloads目录中：

```
wget https://download.teamviewer.com/download/linux/teamviewer_amd64.deb  (最新版本)
wget http://download.teamviewer.com/download/teamviewer_i386.deb
```

TeamViewer安装命令：

```
sudo apt-get install ./teamviewer_11.0.57095_i386.deb
```

注意：不要双击直接安装，或者使用 sudo dpkg -i 命令来安装，因为这个软件的依赖不太好解决，博主使用这种方式安装后在dash中找不到TeamViewer，如果你已经用这种方式安装了，那么可以先卸载掉，然后用上述命令重装。 
TeamViewer卸载命令：

```
cd ~
sudo apt-get remove teamviewer*12
```

dash中搜索Teamviewer，启动后锁定到侧边栏，方便下次启动。

### 安装  asbru-cm

github地址

```
https://github.com/liwenson/asbru-cm
```

**安装方式**

#### Debian / Ubuntu

```
$ curl -s https://packagecloud.io/install/repositories/asbru-cm/asbru-cm/script.deb.sh | sudo bash
$ sudo apt-get install asbru-cm
```

####  Fedora

```
$ curl -s https://packagecloud.io/install/repositories/asbru-cm/asbru-cm/script.rpm.sh | sudo bash
$ sudo dnf install asbru-cm
```

#### Arch / Manjaro

```
yaourt -S asbru-cm-git
```

一旦安装在您的系统上，请输入`/opt/asbru/asbru`您的终端。



### 安装微信

github 地址

```
https://github.com/gatieme/AderXCoding/tree/master/system/tools/electronic_wechat
```

#### 直接下载应用(二进制包)

如果你希望开箱即用，你可以在[`release`](https://github.com/geeeeeeeeek/electronic-wechat/releases)中下载到最新的稳定版本.

```
wget https://github.com/geeeeeeeeek/electronic-wechat/releases/download/v1.4.0/linux-x64.tar.gz
```
github

```
https://github.com/trazyn/weweChat
```

arch 需要安装FUSE来运行 [**AppImage**](https://github.com/trazyn/weweChat/releases/download/v1.1.5/wewechat-1.1.5-x86_64.AppImage)  软件



### 安装FUSE

AppImages需要FUSE才能运行。用户空间中的文件系统（FUSE）是一个让非root用户挂载文件系统的系统。 

在**Ubuntu上**：

```
sudo apt-get install fuse
sudo modprobe fuse
sudo groupadd fuse

user="$(whoami)"
sudo usermod -a -G fuse $user
```

在**openSUSE上**：

```
sudo zypper install fuse
```

在**CentOS / RHEL上**：

```
yum --enablerepo=epel -y install fuse-sshfs # install from EPEL
user="$(whoami)"
usermod -a -G fuse "$user" 
```