# Team 3543 FRC 2016-2017

This README is written in [Markdown](https://daringfireball.net/projects/markdown/syntax)

*FIRST FIRST FIRST*!!!  READ THIS!

If you do not read this and follow the steps in detail, the computer genie will not grant *any* of your wishes.  Read it *carefully*!

## Other Documentation

* [Sensor inventory](sensor_inventory.md)

## Getting started

To make it easier to get going and make sure everyone is coding in the same environment, we are using a tool called *VirtualBox* that runs a *virtual machine* (like a computer-within-a-computer) that runs a standard environment.  It uses an operating system called Linux that is the same one the robot uses and will have all the right tools and libraries already installed.  To use this you will need a decent computer with at least 4GB of memory (8GB would be best) and at least 20GB of free disk space.

Specifically the Linux VM includes:

*  The Ubuntu graphical desktop (similar to Windows or Mac)
*  The [Eclipse](http://eclipse.org) IDE and [Visual Studio Code](https://code.visualstudio.com) code editor
*  Tools for managing your source code [Git](http://git-scm.com)
*  Tools for building (putting together) and deploying (sending to robot) robot code in Java and [Python](http://python.org), namely the [RobotPy](https://robotpy.github.io/) and [pyfrc](http://pyfrc.readthedocs.io/en/latest/) toolkits.

### Make sure you have the required software and accounts

*  Download and install VirtualBox from http://virtualbox.org .  This allows your PC to host the _virtual machine_.
*  Make sure you have created an account for yourself on http://github.com  and that you have been added to the [FRCTeam3543](https://github.com/FRCTeam3543) team. 

### Import the virtual machine

*  A snapshot of the virtual machine is located in the team Google Drive folder in the file called LinuxVM/FRCLinux.vdi .  Download this file to your computer (it will take a while it's pretty big!  We have it on a USB key in the lab as well if you're having trouble with the school WiFi)
*  Double-click the file to import and launch the virtual machine.  You should see a new GUI start up with a fancy new desktop.  This is your new Linux VM running inside your computer.  It's a completely separate computer, so don't expect to see its files or folders in your usual file manager.  Hit the right Ctrl key and F to toggle your Linux VM to and from full-screen mode.

*NOTE* - if your computer has limited memory (less than 8GB) it's a good idea to not be running games or other memory-hogging applications at the same time as the VM.  The VM needs 2GB of your system memory to run.  

Notice that you can _Pause_ and _Resume_ your VM.  That means when you are not using it you can stop it, and then resume it right from where you left off, like closing and opening the lid on a laptop.

When the VM has finished loading you will see a prompt to log in as "FRC Student".  We've made things easy by setting up a single user account that you will customize.  The password for the account is shared in the lab.  You can change it if you want once you log in to your desktop.

### Setting up your VM to work with your GitHub account

Click the "Terminal" icon on the left menu of the desktop.  It's a "shell", a window where you can type in low-level commands.  As a programmer you'll come to appreciate how much more powerful and awesome this is compared to a GUI, though it might not seem like it at first!  I'm just going to refer to it as "the shell" from now on.  You'll use it a lot while programming the robot, for deploying code and talking to GitHub.

**NOTE in all the shell command samples below the `$ ` is there to indicate the prompt, you don't type it, you type everything _after_ it on the line.  You can also copy and paste commands into the shell.**

To talk to the repository on GitHub, you need to generate a pair of keys so that your computer can securely send and retrieve files from GitHub using a secure protocol called SSH. GitHub Here's how to do that:

1.  Open the shell.
2.  At the shell prompt, type `ssh-keygen` and hit &lt;enter&gt;.  Accept all the default choices, *including agreeing to overwrite any existing key* (that one was created when the VM user account was set up and you want a new, personalized one).  Don't forget the password you choose!  It's not the same as your GitHub password, you can make it anything you want.  
3.  After your key pair is generated, at the shell prompt, type `cat ~/.ssh/id_rsa.pub`.  This will display your newly-generated _public key_.  You need to give this key to GitHub, so that when `git` encrypts the data you send to GitHub using your _private key_, GitHub can decrypt it using your _public key_ so it's not just encrypted gibberish!  Select the entire key text and hit Ctrl-Shift-C or Edit > Copy from the menu to copy the key to the clipboard.  **IMPORTANT**  - you need to copy the text in `id_rsa.pub` *not* `id_rsa`.  The second one is your *private key*, which is the "other half" *private key* of this key pair.  It needs to stay private to *you* only, don't copy it around or share it with anyone.  The `.pub` one is the "public half" of the key which is shared with others (like GitHub) so it can decrypt data you have encrypted with your private half of the key.
4.  Launch GitHub.com in your web browser (Firefox on the Ubuntu VM) and log in using the GitHub account you set up earlier.  Click on your avatar at top right, then choose "Settings" from the drop-down menu.
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

1.  First, we need to go to the folder we've set up to hold all your code.  Make sure you have a Terminal launched.  We already cloned a copy of the Github repository in the Linux VM, so you can skip that step.  But for future reference here's what we **would have done**.  Note that a `#` is a comment line in the shell, not a command.  The following shows the complete output in the terminal.  You would type the commands in lines with a `$` prompt.  Again, you can SKIP this step because we already did it when we set up the Linux VM.

```
# Change to the ~/Code directory
student@team3543linux:~$ cd ~/Code

# Clone the repository

student@team3543linux:~/Code$ 
$ git clone git@github.com:FRCTeam3543/Code-2016-17.git
Cloning into 'Code-2016-17'
The authenticity of host 'github.com (192.30.253.113)' can't be established.
RSA key fingerprint is SHA256:nThbg6kXUpJWGl7E1IGOCspRomTxdCARLviKw6E5SY8.
# You would type "yes" and hit Enter on the following line
Are you sure you want to continue connecting (yes/no)? yes
Warning: Permanently added 'github.com,192.30.253.113' (RSA) to the list of known hosts.
# the numbers in the following lines might be different
remote: Counting objects: 84, done.
remote: Compressing objects: 100% (50/50), done.
remote: Total 84 (delta 25), reused 76 (delta 19), pack-reused 0
Unpacking objects: 100% (84/84), done.
Checking connectivity... done.
# Notice that there is now a Code-2016-17 folder containing the project. 'cd' changes to a folder and 'ls' lists a folder's contents
student@team3543linux:~/Code$ cd Code-2016-17/
student@team3543linux:~/Code/Code-2016-17$ ls
experiments  LICENSE.md  README.md
# this shows you what branch your local git is on and what the state is:
student@team3543linux:~/Code/Code-2016-17$ git status
On branch master
nothing to commit, working directory clean

```

2.  Instead, Change to the project directory:
   ```   
   student@team3543linux:~$ cd ~/Code/Code-2016-17
   ```
   If you're curious, the `~` is a Bash-shell shorthand for "My Home Folder".  All this command line stuff is good for you, because once you get using Linux to deploy to the robot you will need it even more!

3.  Make sure the project is up to date.  Note that if you DID NOT generate your key pair properly and add your public key to your Github account's SSH Keys you will get an "access denied" error:

```
# 'pull' gets all the changes from a remote branch.  "origin" is the name of the github server, it's a convention in git to name the 'main' remote server "origin"
# This command says "fetch the master branch from the origin server and merge any changes into my current local branch"
student@team3543linux:~/Code/Code-2016-17$ git pull origin master
# what happens next depends on the state of things (what has changed since we created this VM snapshot).  Something like:
From github.com:FRCTeam3543/Code-2016-17
 * branch            master     -> FETCH_HEAD
Already up-to-date.
```

5.  Launch Visual Studio Code from the left menu and open your new `Code-2016-17` using File &gt; Open.  Or, from your shell do it the fast nerd way.  The following command means "launch visual studio code in _this_ folder" (the `.` means "this folder"):
    ```
    # Note the dot at the end - it means "the current folder"
    student@team3543linux:~/Code/Code-2016-17$ code .
    ```

You're almost ready to code!  Almost!?  Sheesh.  Yeah, you'll get used to this ritual when you're a professional programmer, it's pretty normal for getting new projects set up.

### Not so fast, you don't want your code colliding with other people's code

Alright, so here's where `git` really comes in handy.  When you work with `git`, you can create oodles of _branches_ that allow you to work independently on a feature in the code, and then _merge_ that new feature into the main (*master*) branch when it's ready.  If you type `git status` in the shell, you will probably see you are on *master* branch right now.  Even if you change code though, it will only change it on your _local_ master branch.  To share your changes on GitHub, you would need to _push_ your changes up to the remote repository.

The following instructions show you how to interact with `git` using the shell.  You can also do most of the same things right inside Visual Studio code, and you might find that easier.  Complete the following exercise in the shell, then check out the [guide for using git inside VS Code](https://code.visualstudio.com/Docs/editor/versioncontrol).

Since pushing changes to the remote *master* is a really bad idea--master branch should be reserved for tested, reviewed and ready-to-go-on-the-robot-for-realsies code--you should work in a branch of your own.  To keep things simple (for now), everyone will create a branch using their own name.  In the shell type the following.  We're going to have everyone start off by authoring code in their own branch named after themselves.  So **wherever you see `whatsyername` in the commands type `your first name`**!

```
# Create a branch called whatsername (AGAIN USE YOUR NAME!!) based off the master branch on the github server
$ git branch whatsyername origin/master 
# Now let's "check out" your branch locally.  "Checking out" means "make the my current files match the ones in that branch because I'm going to work on it".
$ git checkout whatsyername
# Now we're going to 'push' your branch up to the remote server, so you can share your work with everyone.
# This command copies your branch to the remote server.  
$ git push -u origin whatsyername
# You can now go to the project on Github in the browser, click on the Code tab and click the "Branches" dropdown.  You should see your branch listed!
# BUT HONESTLY, if I see a branch called "whatsyername" on there ...
```

*NOW* you're ready to code!

### Let's code something!

The great thing about managing code with `git` is it keeps track of all your branches and changes, along with comments and notes about who changed what and when.  If someone changes something in a way that's going to collide with someone else's, it won't allow it!

Now that you are working on your branch, open this `README.md` file in Visual Studio code and edit it.  As an exercise, type your name in between the triple-quotes that follow, just like MK and Ronan did.  So you're not *really* coding yet, but you are editing a file in the project so it's almost the same thing!

```
(Everyone type their first name or initials below this line in the README as an exercise)
MK was here 
Ronan

```

  **BY THE WAY** even the pros MK works with sometimes are not in the habit of putting a `README` file in their project.  **REMEMBER** as a programmer you have *two* main jobs, prioritized as follows:

  1.  Make sure a human **10 years from now** can understand what you did and *why*
  2.  Write code so the computer can understand **now**

  Yup, in **that order**.  A *lot* of people get those backwards.  You program is useless to a computer without #2, but your program is useless to your employer without #1!

Now, save the file.  In Visual Studio Code, you will notice your changes are tracked with a little notification on the git icon in the left sidebar (3rd icon from the top).  Click it and you will get a git menu.  Hover over the README.md file and click the "+" button to tell git you want to commit your change.  Now type a short message about your changes (e.g: added my name to the list) and click the _checkmark_ icon to commit your changes.  

First commit your changes.  You can do this two ways, but the easiest is to use the `git` tools built into Visual Studio Code.  

1.  Select the `git` tool (3rd icon down on the left).  You should see the `README.md` file under "Changes".  Mouse over the filename and click the "+" icon.  This means "I want to commit the changes to this file".  When you are editing multiple files in a set of changes, you will do this for each one.  The file will now be listed under "Staged changes".
2.  Enter a commit message.  This message goes with your change commit and tells others what's in the set of changes.  It should be brief but should identify what's-in-the-box, e.g. "fixed issue #21 where robot would not turn left".  When you've added your message click the checkmark icon to finish committing your changes.
3.  Your committed changes are only added to your _local_ copy of the repository at this point.  To share them with the team, you need to `push` the changes up to the origin.  Click the ellipsis icon (to the right of the checkmark) and select "push" to push your changes up.  You might need to enter your password from when you set up your SSH keys.  This is **not** your Github password, it's the one you protected your private key with.

If all goes well, your branch will be pushed up to GitHub!  Go to the project on GitHub, click on the _Code_ tab, then click the "Branch" dropdown - you should see your `whatsyername` branch there, along with your comments.  Notice how you can switch back and forth in the GUI between the *master* branch, your branch, or anyone else's, and see exactly what changed and when.  magic!  You can submit a "Pull Request" when you want your branch merged into the _master_ branch (meaning "put this in the robot's software for realsies").

Note that you can create as many branches as you want on your local machine and switch back and forth between them - in case you want to try something out or hack around, without affecting your "main" code.  

Rules on "pull requests":

*  all pull requests into master need to be code-reviewed and approved by another team member before they can be merged (Github will enforce this)
*  you should make sure to "pull" changes from the master into your branch often, especially just before you are pushing up code that you intend to be merged via a pull request.  In the shell just do `git pull origin master` in your branch to pull any changes.  If you get CONFLICT warnings ask for help on slack.  We'll cross that bridge when we come to it!

## Running your code

OK, so let's try some example robot code.  In the shell, type:

```
# unless you are already in the right folder
$ cd ~/Code/Code-2016-17
$ ./frc-simulate experiments/RobotPy/robot.py
```

This should run a sample robot using the RobotPy simulator.  To stop the running robot, use *Ctrl-C* in the shell (that's what you do to stop anything that is running in the shell BTW).

## Woohoo?

Did you get this far?  Go on #general in slack and type WOOHOO!!  Or, if you got this far and s__t's not working, go on #general in slack and ask for help!

## More tools and resources

*  `git` will be your best friend and is a key tool nearly all professional programmers use (alas, some are forced to use crappier competitor's tools).  Here are some more resources describing all the cool things it can do: [git - the simple guide](http://rogerdudler.github.io/git-guide/) and [Git Beginners Guide for Dummies](https://backlogtool.com/git-guide/en/).
*  That shell you're typing commands into - it does a lot, and there are lots of really useful Linux commands.  [Here's a tutorial on using the shell](http://linuxcommand.org/learning_the_shell.php)
*  [Learn Python](http://www.learnpython.org/) has a great, hands-on tutorial for learning the Python programming language.  You type your code in a window in the browser and it checks your work.  Do it!
