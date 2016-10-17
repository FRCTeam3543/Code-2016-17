# Team 3543 FRC 2016-2017

This README is written in [Markdown](https://daringfireball.net/projects/markdown/syntax)

## Getting started

To make it easier to get going and make sure everyone is coding in the same environment, we are using a tool called *VirtualBox* that runs a *virtual machine* (like a computer-within-a-computer) that runs a standard environment.  It uses an operating system called Linux that is the same one the robot uses and will have all the right tools and libraries already installed.  To use this you will need a decent computer with at least 4GB of memory (8GB would be best) and at least 20GB of free disk space.

Specifically the Linux VM includes:

*  The Ubuntu graphical desktop (similar to Windows or Mac)
*  The [Eclipse](http://eclipse.org) IDE and [Visual Studio Code](https://code.visualstudio.com) code editor
*  Tools for managing your source code [Git](http://git-scm.com)
*  Tools for building and deploying robot code in Java and [Python](http://python.org), namely the [RobotPy](https://robotpy.github.io/) and [pyfrc](http://pyfrc.readthedocs.io/en/latest/) toolkits.

### What you will need to install on your PC

Note: these instructions are for Windows PC users.  Linux and Mac users likely already have the required tools installed.

*  Download and install VirtualBox from http://virtualbox.org .  This allows your PC to host the _virtual machine_.
*  Make sure you have created an account for yourself on http://github.com  and that you have been added to the [FRCTeam3543](https://github.com/FRCTeam3543) team. 




### Setting up your computer to work with GitHub

When you installed `git`, a program called "Git bash" was installed.  It's a "shell", a window where you can type in low-level commands.  As a programmer you'll come to appreciate how much more powerful and awesome this is compared to a GUI, though it might not seem like it at first!  I'm just going to refer to it as "the shell" from now on.

To talk to the repository on GitHub, you need to generate a pair of keys so that your computer can send and retrieve files from GitHub using a secure protocol called SSH.  Here's how to do that:

1.  Open the "Git Bash" command window that was installed with `git` (the shell).
2.  At the shell prompt, type `ssh-keygen` and hit &lt;enter&gt;.  Accept all the default choices.  Don't forget the password you choose!  It's not the same as your GitHub password, you can make it anything you want.  
3.  After your key pair is generated, at the shell prompt, type `notepad ~/.ssh/id_rsa.pub`.  This will launch Windows Notepad with your _public key_.  You need to give this key to GitHub, so that when `git` encrypts the data you send to GitHub using your _private key_, GitHub can decrypt it using your _public key_ so it's not just encrypted gibberish!  Leave this Notepad window open, you will need it later.
4.  Launch GitHub.com in your web browser (Firefox or Chrome please) and log in using the GitHub account you set up earlier.  Click on your avatar at top right, then choose "Settings" from the drop-down menu.
5.  On the Settings page, select "SSH and GPG keys" from the left side menu.
6.  Click the "New SSH Key" button.  
7.  Enter whatever you want as a title (it's only so you remember it), then go to the Notepad window you opened, select all the text from your key (Ctrl-A) or Edit > Select All) and then copy it to the clipboard (Ctrl-C or Edit > Copy)
8.  Go back to the browser window with GitHub and paste the key into the key area and click "Add SSH Key"

Great!  Your key is set up.  Now let's make sure things are working by _cloning_ the GitHub repository to your local machine so you can do work:

### Getting ready to code

1.  First, you should set up a folder where you'll keep all your code.  Create a Folder on your Desktop (or wherever you want) called Code (or whatever you want).  Here I will assume you created a folder called "Code" on your Desktop, if you didn't then adjust the following accordingly.

2.  Open up a "Git Bash" shell if you don't already have one open (you're a coder now, *always* keep one open!).  **NOTE in all the code samples below the `$ ` is there to indicate the prompt, you don't type it.**

3.  Change to the new directory you created to hold your code:
   ```   
   $ cd ~/Desktop/Code
   ```
   If you're curious, the `~` is a Bash-shell shorthand for "My Home Folder".  All this command line stuff is good for you, because once you get using Linux to deploy to the robot you will need it even more!

4.  Try to _clone_ this repository, using the Git Bash shell:
   ```
   $ git clone git@github.com:FRCTeam3543/Code-2016-17.git
   ```
   If all goes will, you should see a new folder called `Code-2016-17` inside your code folder.  In the shell you can type the following to go into the folder.
   ```
   $ cd Code-2016-17
   ```
   This folder is where you will do all your coding.

5.  Launch Visual Studio Code and open your new `Code-2016-17` using File &gt; Open.  Or, from your shell from step 4 do it the fast nerd way (the command means "launch visual studio code in _this_ folder" (. means "this folder"):
    ```
    $ code .
    ```

You're almost ready to code!  Almost!?  Sheesh.  Yeah, you'll get used to this ritual when you're a professional programmer, it's pretty normal for getting new projects set up.

### Not so fast, you don't want your code colliding with other people's code

Alright, so here's where `git` really comes in handy.  When you work with `git`, you can create oodles of _branches_ that allow you to work independently on a feature in the code, and then _merge_ that new feature into the main (*master*) branch when it's ready.  If you type `git status` in the shell, you will probably see you are on *master* branch right now.  Even if you change code though, it will only change it on your _local_ master branch.  To share your changes on GitHub, you would need to _push_ your changes up to the remote repository.

Since pushing changes to the remote *master* is a really bad idea--master branch should be reserved for tested, reviewed and ready-to-go-on-the-robot-for-realsies-competition-on-the-line code--you should work in a branch of your own.  To keep things simple (for now), everyone will create a branch using their own name.  In the shell type:

```
$ git branch -t yourNameOrInitialsNoSpaces origin/master 
```

This meant "create a branch called `yourNameOrInitialsNoSpaces` that's based off the _master_ branch.  Now, _check out_ your branch so you can work on it.

```
$ git checkout yourNameOrInitialsNoSpaces;
```

*NOW* you're ready to code!

### Let's code something!

The great thing about managing code with `git` is it keeps track of all your branches and changes, along with comments and notes about who changed what and when.  If someone changes something in a way that's going to collide with someone else's, it won't allow it!

Now that you are working on your branch, open this `README.md` file in Visual Studio code and edit it.  As an exercise, type your name in between the triple-quotes that follow:

```
(Everyone type their first name below this line as an exercise)
```

Now, save the file.  In Visual Studio Code, you will notice your changes are tracked with a little notification on the git icon in the left sidebar (3rd icon from the top).  Click it and you will get a git menu.  Hover over the README.md file and click the "+" button to tell git you want to commit these changes.  Now type a short message about your changes (e.g: added my name to the list) and click the _checkmark_ icon to commit your changes.  

Now let's push your changes up to GitHub.  Back in the shell, type the following:

```
$ git push origin yourNameOrInitialsNoSpaces;
```

If all goes well, your branch will be pushed up to GitHub!  Go to the project on GitHub, click on the _Code_ tab, then click the "Branch" dropdown - you should see your yourNameOrInitialsNoSpaces branch there, along with your comments.  Notice how you can switch back and forth in the GUI between the *master* branch, your branch, or anyone else's, and see exactly what changed and when.  You can even pull down anyone else's branch and try it out on your own machine:

```
# this fetches all the remote branches to your local machine
$ git fetch --all
$ git checkout someOtherPersonsBranch
```

Note you will have to commit changes made to your branch before you can switch to someone else's.  But then you can switch back again, and all your changes are restored like magic!

`git` will be your best friend and is a key tool nearly all professional programmers use (alas, some are forced to use crappier competitor's tools).  Here are some more resources describing all the cool things it can do: [git - the simple guide](http://rogerdudler.github.io/git-guide/) and [Git Beginners Guide for Dummies](https://backlogtool.com/git-guide/en/).

TODO - section how to request that someone merge your branch into the master branch.  This is TBD because we still need to establish our code review and merge process _where we test and check each other's work before letting it on the robot!_

## Running your code

Waaaay back at the start of this README you had to install VirtualBox and Vagrant.  Here's where it comes in handy.  What we want is to have a local one-your-machine environment that's pretty close to what things are going to be like on the robot.  So we run a _virtual machine_ with the Linux operating system, which already has the right programming environment, robot deployment tools and simulator on it.  We use VirtualBox to host this computer-within-a-computer on your PC, and Vagrant to set up a standardized environment that's the same on everyone's machine as it is on the machine that builds and deploys to the robot.  This is in order to minimize the kinds if surprises that differences in operating systems can make.

So, to start your virtual machine, open your shell, change directory to your project folder (if you haven't already), and type one simple command:

```
$ cd ~/Desktop/Code/Code-2016-17
$ vagrant up
```

The `vagrant up` machine will take a while the first time, as it has to pull down a virtual machine image from the Internet, and I bet you're at school on that sloooow WiFi.  But it will be pretty fast after that first time.  Meanwhile, you can go on our #general slack channel and call everyone else noobs since you're the first one who got this far, or post something else pithy if you're not.

Once it's done, you can open a shell into your _vagrant box_ (that's what the virtual machine is called) using this command:

```
$ vagrant ssh
```

Now, because the vagrant box is a computer-within-a-computer, the prompt that now appears in your shell is a shell-within-a-shell.  The new shell is being run by your new virtual computer.  The nice thing that vagrant does is it shares your project folder with your vagrant box, in a special folder on that box's filesystem called `/vagrant`.  Check it out - at the shell-in-shell prompt type:

```
$ cd /vagrant
$ ls
```

The `ls` command is Linux-speak for "list the files in this folder", the equivalent of `dir` from an old DOS prompt.  You should see this same `README.md` file listed.  So:

   On your computer `~/Desktop/Code/Code-2016-17`  EQUALS  On vagrant box `/vagrant`

This means you can edit files in Visual Studio Code, and they will be instantly shared on your vagrant box.

*TRICKY* since your vagrant box is essentially a different computer from your PC, you can't type your git commands into this shell, nor can you see any of this computer's files in your file manager, except of course for the ones shared between the two as per above.  So if you want to get back to *your* computer's shell, you just type:

```
$ exit
```

and to get back into the vagrant box
```
$ vagrant ssh
$ cd /vagrant
```

It will get easier and easier once you get used to it.  You might want to keep two shells open--you can run multiple copies of Git Bash at the same time, one that is connected to the vagrant box's shell, and one that is still running your local shell.

OK, so let's try some robot code.  In the vagrant box's shell, type:

```
# unless you are already in the right folder
$ cd /vagrant 
$ ./experiments/RobotPy/robot.py
```

This should run a sample robot using the RobotPy toolkit.  To stop the running robot, use *Ctrl-C* in the shell (that's what you do to stop anything that is running in the shell BTW).

## Woohoo?

Did you get this far?  Go on #general in slack and type WOOHOO!!  Or, if you got this far and s__t's not working, go on #general in slack and ask for help!

