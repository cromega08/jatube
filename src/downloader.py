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