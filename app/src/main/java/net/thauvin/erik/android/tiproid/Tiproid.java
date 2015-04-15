/*
 * @(#)Tiproid.java
 *
 * Copyright (c) 2008, Erik C. Thauvin (http://erik.thauvin.net/)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the authors nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id$
 *
 */
package net.thauvin.erik.android.tiproid;

/**
 * <code>Tiproid</code> is a simple tip calculator for Android.
 *
 * @author  <a href="mailto:erik@thauvin.net">Erik C. Thauvin</a>
 * @version $Revision$, $Date$
 * @created Oct 9, 2008
 * @since   1.0
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Tiproid extends Activity
{
	private static final String PREFS_TIP_RATE = "TipRate";

	/**
	 * Displays the calc dialog.
	 * 
	 * @param editFld The edit field.
	 */
	private void calcDialog(final EditText editFld)
	{
		final LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.calc, null);

		final Button zero = (Button) textEntryView.findViewById(R.id.Button0);
		final Button one = (Button) textEntryView.findViewById(R.id.Button01);
		final Button two = (Button) textEntryView.findViewById(R.id.Button02);
		final Button three = (Button) textEntryView.findViewById(R.id.Button03);
		final Button four = (Button) textEntryView.findViewById(R.id.Button04);
		final Button five = (Button) textEntryView.findViewById(R.id.Button05);
		final Button six = (Button) textEntryView.findViewById(R.id.Button06);
		final Button seven = (Button) textEntryView.findViewById(R.id.Button07);
		final Button eight = (Button) textEntryView.findViewById(R.id.Button08);
		final Button nine = (Button) textEntryView.findViewById(R.id.Button09);
		final Button dot = (Button) textEntryView.findViewById(R.id.ButtonDot);
		final ImageButton bs = (ImageButton) textEntryView.findViewById(R.id.ButtonBS);

		final String curText = editFld.getText().toString();

		final EditText calcEdit = (EditText) textEntryView.findViewById(R.id.calc_edit_fld);

		if (!TextUtils.isEmpty(curText))
		{
			calcEdit.setText(curText);
		}

		zero.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("0");
			}
		});

		one.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("1");
			}
		});

		two.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("2");
			}
		});

		three.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("3");
			}
		});

		four.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("4");
			}
		});

		five.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("5");
			}
		});

		six.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("6");
			}
		});

		seven.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("7");
			}
		});

		eight.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("8");
			}
		});

		nine.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append("9");
			}
		});

		dot.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcEdit.append(".");
			}
		});

		bs.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				final String txt = calcEdit.getText().toString();
				if (!TextUtils.isEmpty(txt))
				{
					if (txt.length() > 0)
					{
						calcEdit.setText(txt.subSequence(0, txt.length() - 1));
						calcEdit.setSelection(txt.length() - 1);
					}
				}
			}
		});

		new AlertDialog.Builder(this).setView(textEntryView).setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{

						editFld.setText(calcEdit.getText().toString());

						editFld.requestFocus();
					}
				}).setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				editFld.requestFocus();
			}
		}).show();
	}

	/**
	 * Calculates the tip and displays the result dialog.
	 * 
	 * @param billTxt The bill amount.
	 * @param taxTxt The tax amount.
	 * @param tipTxt The tip rate.
	 * @param splitTxt The split count.
	 */
	private void calculate(String billTxt, String taxTxt, String tipTxt, String splitTxt)
	{
		if (TextUtils.isEmpty(billTxt))
		{
			Toast.makeText(Tiproid.this, R.string.error_bill_txt, Toast.LENGTH_SHORT).show();
		}
		else
		{
			final int taxTotal;
			if (TextUtils.isEmpty(taxTxt))
			{
				taxTotal = 0;
			}
			else
			{
				taxTotal = parseInt(taxTxt);
			}

			final int billTotal = parseInt(billTxt);

			if (taxTotal == billTotal)
			{
				Toast.makeText(Tiproid.this, R.string.error_identical_txt, Toast.LENGTH_SHORT).show();
			}
			else
			{

				final int subTotal = billTotal - taxTotal;
				final int tipRate = Integer.parseInt(tipTxt);
				final int splitCount = Integer.parseInt(splitTxt);
				int tipSum = (subTotal * tipRate) / 100;
				int billSum = subTotal + tipSum + taxTotal;

				if ((billSum % 100) < 50)
				{
					while ((billSum % 100) != 0)
					{
						tipSum--;
						billSum = subTotal + tipSum + taxTotal;
					}
				}
				else
				{
					while ((billSum % 100) != 0)
					{
						tipSum++;
						billSum = subTotal + tipSum + taxTotal;
					}
				}

				final LayoutInflater factory = LayoutInflater.from(this);
				final View resultView = factory.inflate(R.layout.result, null);

				final TextView tipFld = (TextView) resultView.findViewById(R.id.result_tip_fld);
				final TextView totalFld = (TextView) resultView.findViewById(R.id.result_total_fld);
				final TextView tipLbl = (TextView) resultView.findViewById(R.id.result_tip_lbl);
				final TextView splitFld = (TextView) resultView.findViewById(R.id.result_split_fld);
				final TextView splitLbl = (TextView) resultView.findViewById(R.id.result_split_lbl);

				tipLbl.setText(tipLbl.getText().toString().replace("?", tipTxt));
				tipFld.setText(parseStr(tipSum));
				totalFld.setText(parseStr(billSum));

				if (splitCount == 1)
				{
					splitFld.setText("");
					splitLbl.setText("");
				}
				else
				{
					splitLbl.setText(splitLbl.getText().toString().replace("?", splitTxt));
					int split = billSum / splitCount;
					if ((split * splitCount) < billSum)
					{
						split += 1;
					}
					splitFld.setText(parseStr(split));
				}

				new AlertDialog.Builder(this).setView(resultView).setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int whichButton)
							{
								// do nothing
							}
						}).show();
			}
		}
	}

	/**
	 * Returns the current version number.
	 * 
	 * @return The current version number or empty.
	 */
	private String getVersionNumber()
	{
		try
		{
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (final NameNotFoundException e)
		{
			return "";
		}
	}

	/**
	 * Initializes the various controls.
	 */
	private void init()
	{
		final EditText billFld = (EditText) findViewById(R.id.main_bill_fld);
		final EditText taxFld = (EditText) findViewById(R.id.main_tax_fld);
		final Spinner tipSpin = (Spinner) findViewById(R.id.main_tip_spin);
		final Spinner splitSpin = (Spinner) findViewById(R.id.main_split_spin);

		final Button calcBtn = (Button) findViewById(R.id.main_calculate_btn);
		final Button resetBtn = (Button) findViewById(R.id.main_reset_btn);

		final ImageButton billEditBtn = (ImageButton) findViewById(R.id.main_bill_edit_btn);
		final ImageButton taxEditBtn = (ImageButton) findViewById(R.id.main_tax_edit_btn);

		// Disable edit buttons in landscape mode
		final boolean enableBtns = getWindow().getWindowManager().getDefaultDisplay().getWidth() < getWindow().getWindowManager()
				.getDefaultDisplay().getHeight();
		billEditBtn.setEnabled(enableBtns);
		taxEditBtn.setEnabled(enableBtns);

		if (!enableBtns)
		{
			taxFld.setHint(getString(R.string.main_tax_hint_land_txt));
		}
		else
		{
			taxFld.setHint(getString(R.string.main_tax_hint_txt));
		}

		final ArrayAdapter<CharSequence> tipAdapter = ArrayAdapter.createFromResource(this, R.array.main_tip_array,
				android.R.layout.simple_spinner_item);
		final int defaultTipRate = (tipAdapter.getCount() / 2) - 1;
		tipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tipSpin.setAdapter(tipAdapter);

		final ArrayAdapter<CharSequence> splitAdapter = ArrayAdapter.createFromResource(this, R.array.main_split_array,
				android.R.layout.simple_spinner_item);
		splitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		splitSpin.setAdapter(splitAdapter);

		final SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
		tipSpin.setSelection(settings.getInt(PREFS_TIP_RATE, defaultTipRate));

		calcBtn.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calculate(billFld.getText().toString(), taxFld.getText().toString(), tipSpin.getSelectedItem().toString(), splitSpin
						.getSelectedItem().toString());
			}
		});

		resetBtn.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				billFld.setText("");
				taxFld.setText("");
				splitSpin.setSelection(0);
				tipSpin.setSelection(settings.getInt(PREFS_TIP_RATE, defaultTipRate));
			}
		});

		billEditBtn.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcDialog(billFld);
			}
		});

		taxEditBtn.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View view)
			{
				calcDialog(taxFld);
			}
		});

	}

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, Menu.FIRST, 0, R.string.about_menu_txt).setIcon(android.R.drawable.ic_menu_info_details);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == Menu.FIRST)
		{
			final LayoutInflater factory = LayoutInflater.from(this);
			final View aboutView = factory.inflate(R.layout.about, null);

			new AlertDialog.Builder(this).setView(aboutView).setIcon(android.R.drawable.ic_dialog_info).setTitle(
					getString(R.string.app_name) + ' ' + getVersionNumber()).setPositiveButton(R.string.alert_dialog_ok,
					new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int whichButton)
						{
							// do nothing
						}
					}).show();

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		final SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor = settings.edit();
		final Spinner tipSpin = (Spinner) findViewById(R.id.main_tip_spin);
		editor.putInt(PREFS_TIP_RATE, tipSpin.getSelectedItemPosition());
		editor.commit();
	}

	/**
	 * Parses a given string to an integer, the decimal point is removed.
	 * 
	 * @param str The string to parse.
	 * @return The parsed integer.
	 */
	private int parseInt(String str)
	{
		final int dec = str.lastIndexOf('.');
		final int len = str.length();

		if (dec == -1)
		{
			return Integer.parseInt(str + "00");
		}
		else if (dec == (len - 1))
		{
			return Integer.parseInt(str.substring(0, dec) + "00");
		}
		else if (dec == (len - 2))
		{
			return Integer.parseInt(str.substring(0, dec) + str.substring(dec + 1) + '0');
		}

		return Integer.parseInt(str.substring(0, dec) + str.substring(dec + 1, dec + 3));

	}

	/**
	 * Parses the given integer into to a string, the decimal point is added.
	 * 
	 * @param i The integer.
	 * @return The parsed string.
	 */
	private String parseStr(int i)
	{
		final String s = String.valueOf(i);

		return s.substring(0, s.length() - 2) + '.' + s.substring(s.length() - 2);
	}
}