# How to unzip files in linux
# bzip2 -d filename.bz2
# Note, that this command will not preserve original archive file.
# To preserve the original archive, add the -k option:
# bzip2 -dk filename.bz2


for i in {1987..2008}; do
  echo "downloading start for " $i
  wget http://stat-computing.org/dataexpo/2009/$i.csv.bz2
  echo "downloading done for " $i
  echo "unzipping start for " $i
  bzip2 -dk $i.csv.bz2
  echo "unzipping done for " $i
  echo "removing header start for " $i
  sed -i "1 d" "$i.csv"
  echo "removing header done for " $i
done
echo "now combining all files"
cat *csv > combined.csv

done
