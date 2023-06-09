import os
import re
import subprocess
from string import Template

import local
import workshop


def mod_param(name, mods):
    return ' -{}= "{}" '.format(name, ";".join(mods))


CONFIG_FILE = os.environ["ARMA_CONFIG"]
KEYS = "/arma3/keys"

if not os.path.isdir(KEYS):
    if os.path.exists(KEYS):
        os.remove(KEYS)
    os.makedirs(KEYS)

steamcmd = ["/steamcmd/steamcmd.sh"]
steamcmd.extend(
    [
        "+force_install_dir",
        "/arma3",
        "+login",
        os.environ["STEAM_USERNAME"],
        os.environ["STEAM_PASSWORD"],
        "+app_update",
        "233780",
        "validate",
        "+quit",
    ]
)
subprocess.call(steamcmd)

mods = []

if os.environ["MODS_PRESET"] != "":
    mods.extend(workshop.preset(os.environ["MODS_PRESET"]))

if os.environ["MODS_LOCAL"] == "true" and os.path.exists("mods"):
    mods.extend(local.mods("mods"))

launch = "{} -limitFPS={} -world={} {} {}".format(
    os.environ["ARMA_BINARY"],
    os.environ["ARMA_LIMITFPS"],
    os.environ["ARMA_WORLD"],
    os.environ["ARMA_PARAMS"],
    mod_param("mod", mods),
)

launch += ' -config="/arma3/configs/{}"'.format(CONFIG_FILE)
launch += ' -port={} -name="{}" -profiles="/arma3/configs/profiles"'.format(
    os.environ["ARMA_PORT"], os.environ["ARMA_PROFILE"]
)

if os.path.exists("servermods"):
    launch += mod_param("serverMod", local.mods("servermods"))

print("Launching Arma Server With", launch, flush=True)
os.system(launch)
