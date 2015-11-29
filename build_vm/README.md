# Instructions to set up VM using Vagrant.
### Steps:
* Download & Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads) 
* Download & Install [Vagrant](https://www.vagrantup.com/downloads.html).
* Create a new directory and place this [VagrantFile](https://github.com/SoftwareEngineeringToolDemos/FSE-2011-PSPWizard/blob/master/build_vm/Vagrantfile) in it.
* Navigate to this directory from terminal.
* Run the command "vagrant up". 
  * This will create a new ubuntu 14.04 virtual machine with PSPWizard Tool installed in it for the User to work on.
* Auto-login has been enabled, but if the user gets logged out the credentials for login are :
  * Username: vagrant
  * Password: vagrant

### References:
* https://docs.vagrantup.com/v2/getting-started/up.html
* Vagrant Box used: https://atlas.hashicorp.com/box-cutter/boxes/ubuntu1404-desktop
* https://docs.vagrantup.com/v2/provisioning/shell.html
* https://www.digitalocean.com/community/tutorials/how-to-install-java-on-ubuntu-with-apt-get
* http://stackoverflow.com/questions/17799431/run-jar-file-on-ubuntu-at-system-startup
* https://help.ubuntu.com/community/UnityLaunchersAndDesktopFiles
