import os
import subprocess
from string import Template

import local
import workshop


def mod_param(name, mods):
    return ' -{}= "{}" '.format(name, ";".join(mods))


def env_defined(key):
    return key in os.environ and len(os.environ[key]) > 0


CONFIG_FILE = os.environ["ARMA_CONFIG"]
KEYS = "/arma3/keys"

if not os.path.isdir(KEYS):
    if os.path.exists(KEYS):
        os.remove(KEYS)
    os.makedirs(KEYS)

if os.environ["SKIP_INSTALL"] in ["", "false"]:
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
        ]
    )
    if env_defined("STEAM_BRANCH"):
        steamcmd.extend(["-beta", os.environ["STEAM_BRANCH"]])
    if env_defined("STEAM_BRANCH_PASSWORD"):
        steamcmd.extend(["-betapassword", os.environ["STEAM_BRANCH_PASSWORD"]])
    steamcmd.extend(
        [
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

if os.environ["ARMA_CDLC"] != "":
    for cdlc in os.environ["ARMA_CDLC"].split(";"):
        launch += " -mod={}".format(cdlc)

launch += ' -config="/arma3/configs/{}"'.format(CONFIG_FILE)
launch += ' -port={} -name="{}" -profiles="/arma3/configs/profiles"'.format(
    os.environ["PORT"], os.environ["ARMA_PROFILE"]
)

if os.path.exists("servermods"):
    launch += mod_param("serverMod", local.mods("servermods"))

print("LAUNCHING ARMA SERVER WITH", launch, flush=True)
os.system(launch)
