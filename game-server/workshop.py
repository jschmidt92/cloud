import os
import re
import subprocess
import requests
import keys

from dotenv import load_dotenv

WORKSHOP = "steamapps/workshop/content/107410/"
USER_AGENT = "Mozilla/5.0"

load_dotenv(".env")


def download(mods):
    steamcmd = ["/steamcmd/steamcmd.sh"]
    steamcmd.extend(
        [
            "+force_install_dir",
            "/arma3",
            "+login",
            os.environ["STEAM_USERNAME"],
            os.environ["STEAM_PASSWORD"],
        ]
    )
    for id in mods:
        steamcmd.extend(["+workshop_download_item", "107410", id])
    steamcmd.extend(["+quit"])
    subprocess.call(steamcmd)


def preset(mod_file):
    if mod_file.startswith("http"):
        response = requests.get(mod_file, headers={"User-Agent": USER_AGENT})
        with open("preset.html", "wb") as f:
            f.write(response.content)
        mod_file = "preset.html"

    mods = []
    moddirs = []

    with open(mod_file) as f:
        html = f.read()
        regex = r"filedetails\/\?id=(\d+)\""
        matches = re.finditer(regex, html, re.MULTILINE)

        for _, match in enumerate(matches, start=1):
            mods.append(match.group(1))
            moddir = WORKSHOP + match.group(1)
            moddirs.append(moddir)

        download(mods)

        for moddir in moddirs:
            keys.copy(moddir)

    return moddirs
