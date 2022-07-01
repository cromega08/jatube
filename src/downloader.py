#      <Jatube: A Youtube Downloader application implemented with Java and Python>
#      Copyright (C) <2022>  <Cromega>

#      This program is free software: you can redistribute it and/or modify
#      it under the terms of the GNU Affero General Public License as published
#      by the Free Software Foundation, either version 3 of the License, or
#      (at your option) any later version.

#      This program is distributed in the hope that it will be useful,
#      but WITHOUT ANY WARRANTY; without even the implied warranty of
#      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#      GNU Affero General Public License for more details.

#      You should have received a copy of the GNU Affero General Public License
#      along with this program.  If not, see <https://www.gnu.org/licenses/>.

import pytube as pt
import argparse as ag

parser = ag.ArgumentParser()
parser.add_argument("path", type=str)
parser.add_argument("url", type=str)
parser.add_argument("-a", "--audio", action="store_true")
arguments = parser.parse_args()
yt = pt.YouTube(arguments.url)

match arguments.audio:

    case False:

        file = yt.streams.get_highest_resolution()

    case True:

        file = yt.streams.get_audio_only()

file.download(output_path=arguments.path)
