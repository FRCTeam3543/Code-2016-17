# Team 3543 FRC 2016-2017

This README is written in [Markdown](https://daringfireball.net/projects/markdown/syntax)

## Getting started

To make it easier to get going and make sure everyone is coding in the same environment, we are using a tool called *VirtualBox* that runs a *virtual machine* (like a computer-within-a-computer) that runs a standard environment.  It uses an operating system called Linux that is the same one the robot uses and will have all the right tools and libraries already installed.  To use this you will need a decent computer with at least 4GB of memory (8GB would be best) and at least 20GB of free disk space.

Specifically the Linux VM includes:

*  The Ubuntu graphical desktop (similar to Windows or Mac)
*  The [Eclipse](http://eclipse.org) IDE and [Visual Studio Code](https://code.visualstudio.com) code editor
*  Tools for managing your source code [Git](http://git-scm.com)
*  Tools for building and deploying robot code in Java and [Python](http://python.org), namely the [RobotPy](https://robotpy.github.io/) and [pyfrc](http://pyfrc.readthedocs.io/en/latest/) toolkits.

### Make sure you have the required software and accounts

*  Download and install VirtualBox from http://virtualbox.org .  This allows your PC to host the _virtual machine_.
*  Make sure you have created an account for yourself on http://github.com  and that you have been added to the [FRCTeam3543](https://github.com/FRCTeam3543) team. 

### Import the virtual machine

*  A snapshot of the virtual machine is located in the team Google Drive folder in the file called FRCTeam3542LinuxDestop.vbox .  Download this file to your computer (it will take a while it's pretty big!  We have it on a USB key in the lab as well if you're having trouble with the school WiFi)
*  Double-click the file to import and launch the virtual machine.  You should see a new GUI start up with a fancy new desktop.  This is your new Linux VM running inside your computer.  It's a completely separate computer, so don't expect to see its files or folders in your usual file manager.  Hit the right Ctrl key and F to toggle your Linux VM to and from full-screen mode.

*NOTE* - if your computer has limited memory (less than 8GB) it's a good idea to not be running games or other memory-hogging applications at the same time as the VM.  The VM needs 2GB of your system memory to run.  

Notice that you can _Pause_ and _Resume_ your VM.  That means when you are not using it you can stop it, and then resume it right from where you left off, like closing and opening the lid on a laptop.

When the VM has finished loading you will see a prompt to log in as "FRC Student".  We've made things easy by setting up a single user account that you will customize.  The password for the account is shared in the lab.  You can change it if you want once you log in to your desktop.

### Setting up your VM to work with your GitHub account

Click the console icon on the left menu of the desktop.  It's a "shell", a window where you can type in low-level commands.  As a programmer you'll come to appreciate how much more powerful and awesome this is compared to a GUI, though it might not seem like it at first!  I'm just going to refer to it as "the shell" from now on.  You'll use it a lot while programming the robot, for deploying code and talking to GitHub.

**NOTE in all the shell command samples below the `$ ` is there to indicate the prompt, you don't type it, you type everything _after_ it on the line.  You can also copy and paste commands into the shell.**

To talk to the repository on GitHub, you need to generate a pair of keys so that your computer can send and retrieve files from GitHub using a secure protocol called SSH.  Here's how to do that:

1.  Open the shell.
2.  At the shell prompt, type `ssh-keygen` and hit &lt;enter&gt;.  Accept all the default choices.  Don't forget the password you choose!  It's not the same as your GitHub password, you can make it anything you want.  
3.  After your key pair is generated, at the shell prompt, type `cat ~/.ssh/id_rsa.pub`.  This will display your newly-generated _public key_.  You need to give this key to GitHub, so that when `git` encrypts the data you send to GitHub using your _private key_, GitHub can decrypt it using your _public key_ so it's not just encrypted gibberish!  Leave this Notepad window open, you will need it later.
4.  Launch GitHub.com in your web browser (Firefox or Chrome please) and log in using the GitHub account you set up earlier.  Click on your avatar at top right, then choose "Settings" from the drop-down menu.
5.  On the Settings page, select "SSH and GPG keys" from the left side menu.
6.  Click the "New SSH Key" button.  
7.  Enter whatever you want as a title (it's only so you remember it), then go to the Notepad window you opened, select all the text from your key (Ctrl-A) or Edit > Select All) and then copy it to the clipboard (Ctrl-C or Edit > Copy)
8.  Go back to the browser window with GitHub and paste the key into the key area and click "Add SSH Key"

Great!  Your key is set up.  Next we need to make sure all your code commits are properly attributed to you by setting your name and email.  In the shell, type:

```
$ git config --global user.name "Your Name"
$ git config --global user.email your-email@whereverYourEmailGoes.com
```

Now let's make sure things are working by _cloning_ the GitHub repository to your local machine so you can do work.

### Getting ready to code

1.  First, we need to go to the folder we've set up to hold all your code.  Make sure you have a Terminal launched.

2.  Change to the code directory:
   ```   
   $ cd ~/Code
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

5.  Launch Visual Studio Code from the left menu and open your new `Code-2016-17` using File &gt; Open.  Or, from your shell from step 4 do it the fast nerd way.  The following command means "launch visual studio code in _this_ folder" (. means "this folder"):
    ```
    $ code .
    ```

You're almost ready to code!  Almost!?  Sheesh.  Yeah, you'll get used to this ritual when you're a professional programmer, it's pretty normal for getting new projects set up.

### Not so fast, you don't want your code colliding with other people's code

Alright, so here's where `git` really comes in handy.  When you work with `git`, you can create oodles of _branches_ that allow you to work independently on a feature in the code, and then _merge_ that new feature into the main (*master*) branch when it's ready.  If you type `git status` in the shell, you will probably see you are on *master* branch right now.  Even if you change code though, it will only change it on your _local_ master branch.  To share your changes on GitHub, you would need to _push_ your changes up to the remote repository.

Since pushing changes to the remote *master* is a really bad idea--master branch should be reserved for tested, reviewed and ready-to-go-on-the-robot-for-realsies code--you should work in a branch of your own.  To keep things simple (for now), everyone will create a branch using their own name.  In the shell type:

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
(Everyone type their first name or initials below this line in the README as an exercise)
MK was here

```

Now, save the file.  In Visual Studio Code, you will notice your changes are tracked with a little notification on the git icon in the left sidebar (3rd icon from the top).  Click it and you will get a git menu.  Hover over the README.md file and click the "+" button to tell git you want to commit your change.  Now type a short message about your changes (e.g: added my name to the list) and click the _checkmark_ icon to commit your changes.  

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

OK, so let's try some robot code.  In the shell, type:

```
# unless you are already in the right folder
$ cd ~/Code
$ ./experiments/RobotPy/robot.py
```

This should run a sample robot using the RobotPy toolkit.  To stop the running robot, use *Ctrl-C* in the shell (that's what you do to stop anything that is running in the shell BTW).

## Woohoo?

Did you get this far?  Go on #general in slack and type WOOHOO!!  Or, if you got this far and s__t's not working, go on #general in slack and ask for help!

